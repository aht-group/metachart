package org.metachart.factory.xhtml.chart.apex;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.IntStream;

import org.exlp.interfaces.system.property.Configuration;
import org.jdom2.Document;
import org.jdom2.Element;
import org.metachart.client.McCoreBootstrap;
import org.metachart.factory.json.chart.apex.JsonTitleFactory;
import org.metachart.factory.json.chart.apex.JsonToolbarFactory;
import org.metachart.model.json.chart.apex.JsonApex;
import org.metachart.model.json.chart.apex.JsonChart;
import org.metachart.model.json.chart.apex.JsonSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import net.sf.exlp.util.io.JsonUtil;
import net.sf.exlp.util.xml.JDomUtil;

public class CliApexHeatbarXhtmlFactory
{
	final static Logger logger = LoggerFactory.getLogger(CliApexHeatbarXhtmlFactory.class);
	
	public CliApexHeatbarXhtmlFactory()
	{
		
	}
	
	public Document tableHeatbar()
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
		Element metaViewport = new Element("meta"); metaViewport.setAttribute("name","viewport"); metaViewport.setAttribute("content","width=device-width, initial-scale=1.0");
		Element title = new Element("title"); title.addContent("Heatbar");
		Element script = new Element("script"); script.setText("cx"); script.setAttribute("src","https://cdn.jsdelivr.net/npm/apexcharts");
		
		Element head = new Element("head");
		head.getChildren().add(metaCharset);
		head.getChildren().add(metaViewport);
		head.getChildren().add(title);
		head.getChildren().add(script);
		return head;
	}
	
	private Element body()
	{
		Element div = new Element("div");
		div.setAttribute("id","chart1");
		div.setAttribute("class","metachart-heatbar-table");
		
		StringBuilder sb = new StringBuilder();
		sb.append("var options=");
		try
		{
			sb.append("\n").append(JsonUtil.toPrettyString(apex()));
		}
		catch (JsonProcessingException e) {e.printStackTrace();}
		sb.append("\n\n").append("var chart1 = new ApexCharts(document.querySelector(\"#chart1\"), options);");
		sb.append("\n").append("chart1.render();");
		
		Element script = new Element("script");
		script.addContent(sb.toString());
		
		Element body = new Element("body");
		body.getChildren().add(div);
		body.getChildren().add(script);
		return body;
	}
	
	private JsonApex apex()
	{
		JsonChart chart = new JsonChart();
		chart.setType(JsonChart.Type.heatmap.toString());
		chart.setToolbar(JsonToolbarFactory.build(false));
		chart.setHeight(100);
		
		JsonApex apex = new JsonApex();
		apex.setChart(chart);
		apex.setTitle(JsonTitleFactory.build("", false));
		apex.setSeries(new ArrayList<>());
		this.appySeries(apex);
		return apex;
	}
	
	private void appySeries(JsonApex apex)
	{
		apex.setSeries(new ArrayList<>());
		
		JsonSeries s1 = new JsonSeries();
		s1.setName("Test");
		
		s1.setList(new ArrayList<>());
		s1.getList().add(IntStream.of(1,5).boxed().toArray(Integer[]::new));
		s1.getList().add(IntStream.of(2,10).boxed().toArray(Integer[]::new));
		s1.getList().add(IntStream.of(3,7).boxed().toArray(Integer[]::new));
		
		apex.getSeries().add(s1);
	}
	
	public static void main (String[] args) throws Exception
	{		
		Configuration config = McCoreBootstrap.init();
		Path path = Paths.get(config.getString(McCoreBootstrap.cfgDirTmp));
		logger.info("Wrting to "+path.toString());
		
		CliApexHeatbarXhtmlFactory cli = new CliApexHeatbarXhtmlFactory();
		
		JDomUtil.instance().omitDeclaration(true).write(cli.tableHeatbar(),path.resolve("table-heatbar.html"));
	}
}
