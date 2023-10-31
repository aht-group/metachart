package org.metachart.factory.xhtml.chart.echart;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.exlp.interfaces.system.property.Configuration;
import org.jdom2.Document;
import org.jdom2.Element;
import org.metachart.factory.json.chart.EchartProvider;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.txt.chart.XhtmlEchartFactory;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.xml.JDomUtil;

public class CliEchartType
{
	final static Logger logger = LoggerFactory.getLogger(CliEchartType.class);
	
	private final Path path;
	private final XhtmlEchartFactory xhfEchart;
	
	public CliEchartType(Configuration config)
	{
		xhfEchart = XhtmlEchartFactory.instance();
		
		path = Paths.get(config.getString(McBootstrap.cfgDirTmp));
		logger.info("Wrting to "+path.toString());
	}
	
	public Document html(JsonEchartFactory.Type type) throws IOException
	{
		Element html = new Element("html");
		html.setAttribute("lang","en");
		html.getChildren().add(xhfEchart.head("Demo: "+type));
		
		StringWriter sw = new StringWriter();
		EchartProvider.demo(sw,type,xhfEchart.getDivCntainerId());
		
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
		CliEchartType cli = new CliEchartType(config);
		
//		cli.html(JsonEchartFactory.Type.sankey);
		cli.html(JsonEchartFactory.Type.heatmap);
	}
}
