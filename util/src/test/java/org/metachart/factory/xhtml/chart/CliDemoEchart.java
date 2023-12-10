package org.metachart.factory.xhtml.chart;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.exlp.interfaces.system.property.Configuration;
import org.jdom2.Document;
import org.jdom2.Element;
import org.metachart.factory.json.chart.EchartProvider;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.js.demo.EchartGraphDemo;
import org.metachart.factory.json.chart.echart.js.demo.EchartLineDemo;
import org.metachart.factory.json.chart.echart.js.type.JsonEchartGraphFactory;
import org.metachart.factory.json.chart.echart.js.type.JsonEchartLineFactory;
import org.metachart.factory.xhtml.XhtmlEchartFactory;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.xml.JDomUtil;

public class CliDemoEchart
{
	final static Logger logger = LoggerFactory.getLogger(CliDemoEchart.class);
	
	private final Path path;
	private final XhtmlEchartFactory xhfEchart;
	
	public CliDemoEchart(Configuration config)
	{
		xhfEchart = XhtmlEchartFactory.instance();
		
		path = Paths.get(config.getString(McBootstrap.cfgDirTmp));
		logger.info("Wrting to "+path.toString());
	}
	
	public void demo(JsonEchartFactory.Type type) throws IOException
	{	
		Path p = path.resolve("echart-"+type.toString()+".html");
		switch(type)
		{
			case line: JsonEchartLineFactory.instance().xhtml(p, EchartLineDemo.instance()); break;
			case sankey: this.html(type); break;
			case heatmap: this.html(type); break;
			case heatbar: this.html(type); break;
			case graph: JsonEchartGraphFactory.instance().xhtml(p, EchartGraphDemo.instance()); break;
		}
	}
	
	public Document html(JsonEchartFactory.Type type) throws IOException
	{
		Element html = new Element("html");
		html.setAttribute("lang","en");
		html.getChildren().add(xhfEchart.head("Demo: "+type));
		
		StringWriter sw = new StringWriter();
		EchartProvider.instance(sw).demo(type,xhfEchart.getDivCntainerId());
		
		html.getChildren().add(xhfEchart.body(sw.toString()));

        Document doc = new Document(html);
        doc.setDocType(new org.jdom2.DocType("html"));
		
		JDomUtil.instance().omitDeclaration(true).info(doc);
		JDomUtil.instance().omitDeclaration(true).write(doc,path.resolve("echart-"+type.toString()+".html"));
		return doc;
	}
	
	public static void main (String[] args) throws Exception
	{		
		Configuration config = McBootstrap.init();
		CliDemoEchart cli = new CliDemoEchart(config);
		
		cli.demo(JsonEchartFactory.Type.line);
		cli.html(JsonEchartFactory.Type.sankey);
		cli.html(JsonEchartFactory.Type.heatmap);
		cli.demo(JsonEchartFactory.Type.heatbar);
		cli.demo(JsonEchartFactory.Type.graph);
	}
}