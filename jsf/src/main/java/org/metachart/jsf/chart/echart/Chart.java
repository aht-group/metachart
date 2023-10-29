package org.metachart.jsf.chart.echart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.PostAddToViewEvent;

import org.exlp.controller.handler.web.jsf.ExlpJsfHandler;
import org.metachart.factory.json.chart.echart.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.JsonTitleFactory;
import org.metachart.factory.txt.chart.echart.TxtApacheChartFactory;
import org.metachart.factory.txt.chart.echart.TxtEchartFunctioFactory;
import org.metachart.factory.txt.chart.echart.TxtRandomDataFactory;
import org.metachart.jsf.common.Title;
import org.metachart.model.json.chart.apache.JsonApache;
import org.metachart.model.json.chart.apache.JsonSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.io.JsonUtil;

@FacesComponent(value="org.metachart.jsf.chart.echart.Chart")
@ListenerFor(systemEventClass=PostAddToViewEvent.class)
public class Chart extends UINamingContainer
{
	final static Logger logger = LoggerFactory.getLogger(Chart.class);

	private String chartId; public String getChartId() {return chartId;}
	public void setCodeId(String chartId) {this.chartId = chartId;}
	
	private Title title;

	public Chart()
	{
		chartId = UUID.randomUUID().toString().replaceAll("-","");
	}
 
	@Override
	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException
	{
		super.processEvent(event);
		ExlpJsfHandler.pushJsToHead(this.getFacesContext(), "echarts", "5.4.2/echarts.min.js");
	}
	
	@Override
 	public void encodeBegin(FacesContext ctx) throws IOException
 	{
		logger.info("encodeBegin: "+chartId);
		ResponseWriter writer = ctx.getResponseWriter();
        writer.write("<div id=\""+chartId+"\" class=\"e-chart\"></div>");

//		Map<String,Object> map = this.getAttributes();
//		this.data         = (String) map.get(Attribute.data.toString());
//		this.container    = (PivotSettings) map.get(Attribute.container.toString());
		
		this.encodeChildren(ctx);
	}
	
	@Override
	public void encodeChildren(FacesContext ctx) throws IOException
	{
		logger.info("encodeChildren: "+chartId);
        for (UIComponent child : getChildren())
        {
        	logger.info(child.getClass().getName());
        	if(child instanceof org.metachart.jsf.common.Title)
        	{
        		child.encodeAll(ctx);
        		title = (org.metachart.jsf.common.Title)child;
        	}
        }
    }
	
	@Override
 	public void encodeEnd(FacesContext ctx) throws IOException
 	{
		logger.info("encodeEnd: "+chartId);
		ResponseWriter writer = ctx.getResponseWriter();
		
		writer.startElement("script", this);
		
		String varChar = "myChart";
		String fRandom = "randomData";
		
		JsonUtil jom = JsonUtil.instance();
		TxtApacheChartFactory txtChart = TxtApacheChartFactory.instance(writer,jom).varChart(varChar);
		TxtRandomDataFactory tfRandom = TxtRandomDataFactory.instance().writer(writer);
		TxtEchartFunctioFactory tfFunction = TxtEchartFunctioFactory.instance().writer(writer);
		
		txtChart.declare(chartId,JsonHtmlFactory.build("canvas",false));
		tfRandom.randomDataDate();
		txtChart.option(apache());
		tfFunction.pushRandomData(varChar,fRandom);
		txtChart.init();
		
		writer.endElement("script");
        writer.writeText(System.getProperty("line.separator"), null);
 	}
	
	private JsonApache apache()
	{
		JsonSeries series = new JsonSeries();
		series.setName("Fake Data");
		series.setType("line");
		series.setShowSymbol(false);
		series.setData("data");
		
		JsonApache apache = new JsonApache();
		apache.setTitle(JsonTitleFactory.build(title));
		apache.setAxisX(JsonAxisFactory.build("time"));
		apache.setAxisY(JsonAxisFactory.build("value"));
		apache.setSeries(new ArrayList<>());
		apache.getSeries().add(series);
		return apache;
	}
}