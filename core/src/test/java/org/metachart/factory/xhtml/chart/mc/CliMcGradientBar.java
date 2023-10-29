package org.metachart.factory.xhtml.chart.mc;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.exlp.interfaces.system.property.Configuration;
import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;
import org.metachart.factory.json.chart.mc.JsonMcGradientBarFactory;
import org.metachart.factory.json.function.JsonRandomFunctions;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.xml.JDomUtil;

public class CliMcGradientBar
{
	final static Logger logger = LoggerFactory.getLogger(CliMcGradientBar.class);
	
	public CliMcGradientBar()
	{
		
	}
	
	public Document tableHeatbar() throws IOException
	{
		Element html = new Element("html");
		html.setAttribute("lang","en");
		
		html.getChildren().add(head());
		html.getChildren().add(body());

        Document doc = new Document(html);
        doc.setDocType(new org.jdom2.DocType("html"));
		
		JDomUtil.instance().omitDeclaration(true).omitEscape(true).info(doc);
		return doc;
	}
	
	private Element head()
	{
		Element metaCharset = new Element("meta"); metaCharset.setAttribute("charset","UTF-8");
		Element title = new Element("title"); title.addContent("MetaChart Heatbar");
		Element script = new Element("script"); script.setAttribute("src","app.js");
		
//		Element css = new Element("style");
//		css.setText("* {margin: 0; padding: 0;}"
//				+ "\n#chart-container {position: relative; height: 100vh; overflow: hidden;}");
		
		Element head = new Element("head");
		head.getChildren().add(metaCharset);
		head.getChildren().add(title);
		head.getChildren().add(script);
//		head.getChildren().add(css);
		return head;
	}
	
	private Element body() throws IOException
	{
		Element canvas = new Element("canvas");
		canvas.setAttribute("id","cont");
		canvas.setAttribute("width","400");
		canvas.setAttribute("height","50");
		
		Element script = new Element("script");
		script.addContent(echart());
		
		Element body = new Element("body");
		body.getChildren().add(canvas);
		body.getChildren().add(script);
		return body;
	}
	
	private String echart() throws IOException
	{
		StringWriter sw = new StringWriter();
		
		JsonMcGradientBarFactory jfHeatbar = JsonMcGradientBarFactory.instance(sw);
		JsonRandomFunctions jfRandom = JsonRandomFunctions.instance(sw);
		
		jfHeatbar.init("cont");
		jfHeatbar.addRandomData();
		jfRandom.randomInt();

		return sw.toString();
	}
	
	public static void main (String[] args) throws Exception
	{		
		Configuration config = McBootstrap.init();
		Path path = Paths.get(config.getString(McBootstrap.cfgDirTmp));
		logger.info("Wrting to "+path.toString());
		
		CliMcGradientBar cli = new CliMcGradientBar();
		
		JDomUtil.instance().omitDeclaration(true).omitEscape(true).write(cli.tableHeatbar(),path.resolve("mc-heatbar.html"));
	}
}
