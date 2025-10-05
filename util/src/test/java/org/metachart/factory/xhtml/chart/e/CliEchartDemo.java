package org.metachart.factory.xhtml.chart.e;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.exlp.interfaces.system.property.Configuration;
import org.exlp.util.io.JsonUtil;
import org.jdom2.Document;
import org.jdom2.Element;
import org.metachart.factory.json.chart.EchartProvider;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoTime;
import org.metachart.factory.json.chart.echart.js.family.JsonEchartTimeFactory;
import org.metachart.factory.xhtml.XhtmlEchartFactory;
import org.metachart.model.json.chart.echart.JsonEchart;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.xml.JDomUtil;

public class CliEchartDemo
{
	final static Logger logger = LoggerFactory.getLogger(CliEchartDemo.class);

	private final Path path;
	private final XhtmlEchartFactory xhfEchart;

	public CliEchartDemo(Configuration config)
	{
		xhfEchart = XhtmlEchartFactory.instance();

		path = Paths.get(config.getString(McBootstrap.cfgDirTmp));
		logger.info("Wrting to "+path.toString());
	}

	public void option() throws IOException
	{	
		Element html = new Element("html");
		html.setAttribute("lang","en");
		html.getChildren().add(xhfEchart.head("Demo: Option"));

		StringWriter sw = new StringWriter();
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(sw,JsonUtil.instance()).declare(xhfEchart.getDivId(),JsonHtmlFactory.build(JsonHtmlFactory.Renderer.canvas,false));
		
		JsonEchartTimeFactory.instance(sw).id(xhfEchart.getDivId()).json(null, EchartDemoTime.toDatas(), EchartDemoTime.toOption());
	
		html.getChildren().add(xhfEchart.body(sw.toString()));

        Document doc = new Document(html);
        doc.setDocType(new org.jdom2.DocType("html"));

		JDomUtil.instance().omitDeclaration(true).info(doc);
		JDomUtil.instance().omitDeclaration(true).write(doc,path.resolve("echart-option.demo.html"));
	}
	
	public void demo(JsonEchart.Type type) throws IOException
	{
		Element html = new Element("html");
		html.setAttribute("lang","en");
		html.getChildren().add(xhfEchart.head("Demo: "+type));

		StringWriter sw = new StringWriter();
		EchartProvider.instance(sw).demo(type,xhfEchart.getDivId());

		html.getChildren().add(xhfEchart.body(sw.toString()));

        Document doc = new Document(html);
        doc.setDocType(new org.jdom2.DocType("html"));

		JDomUtil.instance().omitDeclaration(true).info(doc);
		JDomUtil.instance().omitDeclaration(true).write(doc,path.resolve("echart-"+type.toString()+".demo.html"));
	}
	
	public void all() throws IOException
	{
		for(JsonEchart.Type type : JsonEchart.Type.values())
		{
			this.demo(type);
		}
	}
	
	public void single() throws IOException
	{
		this.demo(JsonEchart.Type.time);
//		this.demo(JsonEchartFactory.Type.line);
//		this.demo(JsonEchartFactory.Type.gauge);

//		cli.demo(JsonEchartFactory.Type.heatbar);
//		cli.demo(JsonEchartFactory.Type.graph);
	}

	public static void main (String[] args) throws Exception
	{
		Configuration config = McBootstrap.init();
		CliEchartDemo cli = new CliEchartDemo(config);

		cli.option();
		cli.all();
//		cli.single();
	}
}