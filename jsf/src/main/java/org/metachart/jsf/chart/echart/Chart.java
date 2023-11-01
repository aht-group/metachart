package org.metachart.jsf.chart.echart;

import java.io.IOException;
import java.util.Objects;
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

import org.exlp.util.JsfUtil;
import org.exlp.util.jx.ComponentAttribute;
import org.metachart.factory.json.chart.EchartProvider;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.jsf.common.Title;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.chart.echart.Chart")
@ListenerFor(systemEventClass=PostAddToViewEvent.class)
public class Chart extends UINamingContainer
{
	final static Logger logger = LoggerFactory.getLogger(Chart.class);

	private static enum Attribute {scope,type}
	
	private String chartId; public String getChartId() {return chartId;}
	public void setCodeId(String chartId) {this.chartId = chartId;}
	
	private String type;
	private String scope;
	
	private Title title;

	public Chart()
	{
		chartId = UUID.randomUUID().toString().replaceAll("-","");
	}
 
	@Override
	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException
	{
		super.processEvent(event);
		JsfUtil.pushJsToHead(this.getFacesContext(), "echarts", "5.4.2/echarts.min.js");
	}
	
	@Override
 	public void encodeBegin(FacesContext ctx) throws IOException
 	{
		logger.info("encodeBegin: "+chartId);
		
		type = ComponentAttribute.toString(ctx,this,Attribute.type);
		scope = ComponentAttribute.toString(ctx,this,Attribute.scope);
		
		ResponseWriter writer = ctx.getResponseWriter();
        writer.write("<div id=\""+chartId+"\" class=\"e-chart\"></div>");
		
		this.encodeChildren(ctx);
	}
	
	@Override
	public void encodeChildren(FacesContext ctx) throws IOException
	{
		logger.info("encodeChildren: "+chartId);
        for (UIComponent child : getChildren())
        {
        	logger.info(child.getClass().getName());
        	child.encodeAll(ctx);
        	if(child instanceof org.metachart.jsf.common.Title) {title = (org.metachart.jsf.common.Title)child;}
        }
    }
	
	@Override
 	public void encodeEnd(FacesContext ctx) throws IOException
 	{
		logger.info("encodeEnd: "+chartId);
		ResponseWriter writer = ctx.getResponseWriter();
		
		writer.startElement("script", this);
		
		if(Objects.nonNull(type))
		{
			if(Objects.nonNull(scope) && scope.equals("demo")) {EchartProvider.demo(writer,type,chartId);}
			else
			{
				
			}
		}
		
		writer.endElement("script");
        writer.writeText(System.getProperty("line.separator"), null);
 	}
}