package org.metachart.factory.xhtml.chart.echart;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.exlp.interfaces.system.property.Configuration;
import org.jdom2.Document;
import org.jdom2.Element;
import org.metachart.factory.json.chart.echart.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.JsonTitleFactory;
import org.metachart.factory.txt.chart.echart.TxtApacheChartFactory;
import org.metachart.factory.txt.chart.echart.TxtEchartFunctioFactory;
import org.metachart.factory.txt.chart.echart.TxtRandomDataFactory;
import org.metachart.model.json.chart.apache.JsonApache;
import org.metachart.model.json.chart.apache.JsonSeries;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.io.JsonUtil;
import net.sf.exlp.util.xml.JDomUtil;

public class CliEchartDynamic
{
	final static Logger logger = LoggerFactory.getLogger(CliEchartDynamic.class);
	
	public CliEchartDynamic()
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
		
		JDomUtil.instance().omitDeclaration(true).info(doc);
		return doc;
	}
	
	private Element head()
	{
		Element metaCharset = new Element("meta"); metaCharset.setAttribute("charset","UTF-8");
		Element title = new Element("title"); title.addContent("Dynamic Data + Time Axis");
		Element script = new Element("script"); script.setText("cx"); script.setAttribute("src","https://fastly.jsdelivr.net/npm/echarts@5.4.3/dist/echarts.min.js");
		
		Element css = new Element("style");
		css.setText("* {margin: 0; padding: 0;}"
				+ "\n#chart-container {position: relative; height: 100vh; overflow: hidden;}");
		
		Element head = new Element("head");
		head.getChildren().add(metaCharset);
		head.getChildren().add(title);
		head.getChildren().add(script);
		head.getChildren().add(css);
		return head;
	}
	
	private Element body() throws IOException
	{
		Element div = new Element("div");
		div.setAttribute("id","chart-container");
		div.setAttribute("class","metachart-apache-dynamic");
		
		Element script = new Element("script");
		script.addContent(echart());
		
		Element body = new Element("body");
		body.getChildren().add(div);
		body.getChildren().add(script);
		return body;
	}
	
	private String echart() throws IOException
	{
		StringWriter sw = new StringWriter();
		
		String varChar = "myChart";
		String fRandom = "randomData";
		
		JsonUtil jom = JsonUtil.instance();
		TxtApacheChartFactory txtChart = TxtApacheChartFactory.instance(sw,jom).varChart(varChar);
		TxtRandomDataFactory tfRandom = TxtRandomDataFactory.instance().writer(sw);
		TxtEchartFunctioFactory tfFunction = TxtEchartFunctioFactory.instance().writer(sw);
		
		txtChart.declare("chart-container",JsonHtmlFactory.build("canvas",false));
		tfRandom.randomDataDate();
		txtChart.option(apache());
		tfFunction.pushRandomData(varChar,fRandom);
		txtChart.init();
		return sw.toString();
	}
	
	private JsonApache apache()
	{
		JsonSeries series = new JsonSeries();
		series.setName("Fake Data");
		series.setType("line");
		series.setShowSymbol(false);
		series.setData("data");
		
		JsonApache apache = new JsonApache();
		apache.setTitle(JsonTitleFactory.build("Time Axis"));
		apache.setAxisX(JsonAxisFactory.build("time"));
		apache.setAxisY(JsonAxisFactory.build("value"));
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
