package org.metachart.factory.xhtml.chart.echart;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.exlp.interfaces.system.property.Configuration;
import org.exlp.util.io.JsonUtil;
import org.jdom2.Document;
import org.jdom2.Element;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.grid.JsonTitleFactory;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.function.TxtEchartFunctionFactory;
import org.metachart.factory.json.function.TxtRandomDataFactory;
import org.metachart.factory.xhtml.XhtmlEchartFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.xml.JDomUtil;

public class CliEchartDynamic
{
	final static Logger logger = LoggerFactory.getLogger(CliEchartDynamic.class);
	
	private final XhtmlEchartFactory xhfEchart;
	
	
	public CliEchartDynamic()
	{
		xhfEchart = XhtmlEchartFactory.instance();
	}
	
	public Document tableHeatbar() throws IOException
	{
		Element html = new Element("html");
		html.setAttribute("lang","en");
		
		html.getChildren().add(xhfEchart.head("Demo: "));
		html.getChildren().add(xhfEchart.body(echart()));

        Document doc = new Document(html);
        doc.setDocType(new org.jdom2.DocType("html"));
		
		JDomUtil.instance().omitDeclaration(true).info(doc);
		return doc;
	}	
	
	private String echart() throws IOException
	{
		StringWriter sw = new StringWriter();
		
		String fRandom = "randomData";
		
		JsonUtil jom = JsonUtil.instance();
		JsonEchartFactory txtChart = JsonEchartFactory.instance(sw,jom);
		TxtRandomDataFactory tfRandom = TxtRandomDataFactory.instance().writer(sw);
		TxtEchartFunctionFactory tfFunction = TxtEchartFunctionFactory.instance().writer(sw);
		
		txtChart.declare(xhfEchart.getDivId(),JsonHtmlFactory.build(JsonHtmlFactory.Renderer.canvas,false));
		txtChart.letData();
		tfRandom.randomDataDate();
		txtChart.option(apache());
		tfFunction.pushRandomData(txtChart.getVarChart(),fRandom);
		txtChart.init();
		return sw.toString();
	}
	
	private JsonOption apache()
	{
		JsonSeries series = new JsonSeries();
		series.setName("Fake Data");
		series.setType("line");
		series.setShowSymbol(false);
		series.setData("data");
		
		JsonOption apache = new JsonOption();
		apache.setTitle(JsonTitleFactory.build("Time Axis"));
		apache.setAxisX(JsonAxisFactory.instance().type("time").build());
		apache.setAxisY(JsonAxisFactory.instance().type("value").build());
		apache.setSeries(new ArrayList<>());
		apache.getSeries().add(series);
		return apache;
	}
	
	public static void main (String[] args) throws Exception
	{		
		Configuration config = McBootstrap.init();
		Path path = Paths.get(config.getString(McBootstrap.cfgDirTmp));
		logger.info("Wrting to "+path.toString());
		
		CliEchartDynamic cli = new CliEchartDynamic();
		
		JDomUtil.instance().omitDeclaration(true).write(cli.tableHeatbar(),path.resolve("echart-dynamic-random.html"));
	}
}
