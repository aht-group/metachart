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

import org.apache.commons.lang3.ObjectUtils;
import org.exlp.util.JsfUtil;
import org.exlp.util.jx.ComponentAttribute;
import org.metachart.factory.json.chart.EchartProvider;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.grid.JsonGridFactory;
import org.metachart.factory.json.chart.echart.type.JsonEchartHeatbarFactory;
import org.metachart.jsf.common.Data;
import org.metachart.jsf.common.Title;
import org.metachart.model.json.chart.echart.grid.JsonGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.io.StringUtil;

@FacesComponent(value="org.metachart.jsf.chart.echart.Chart")
@ListenerFor(systemEventClass=PostAddToViewEvent.class)
public class Chart extends UINamingContainer
{
	final static Logger logger = LoggerFactory.getLogger(Chart.class);

	private static enum Attribute {scope,type,height}
	
	private String chartId;
	
	private String type;
	private String scope;
	private String height;
	
	private JsonGrid grid;
	
	private Title title;
	private Data data;
 
	@Override
	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException
	{
		super.processEvent(event);
		JsfUtil.pushJsToHead(this.getFacesContext(), "echarts", "5.4.2/echarts.min.js");
	}
	
	@Override
 	public void encodeBegin(FacesContext ctx) throws IOException
 	{
//		logger.info(StringUtil.stars());
//		logger.info("ID:"+super.getId());
//		logger.info("ClientID:"+super.getClientId());
//		logger.info("Parent.ID:"+super.getParent().getId());
//		logger.info("Parent.ClientID:"+super.getParent().getClientId());
		
		chartId = super.getClientId().replace(":","_");
		scope = ComponentAttribute.toString(ctx,this,Attribute.scope);
		type = ComponentAttribute.toString(ctx,this,Attribute.type);
		height = ComponentAttribute.toString(ctx,this,Attribute.height);

		if(ObjectUtils.anyNotNull(height)) {grid = JsonGridFactory.instance().size(height,null).build();}
		
		this.encodeChildren(ctx);
	}
	
	@Override
	public void encodeChildren(FacesContext ctx) throws IOException
	{
        for (UIComponent child : getChildren())
        {
        	logger.info(child.getClass().getName());
        	child.encodeAll(ctx);
        	if(child instanceof org.metachart.jsf.common.Title) {title = (org.metachart.jsf.common.Title)child;}
        	if(child instanceof org.metachart.jsf.common.Data) {data = (org.metachart.jsf.common.Data)child;}
        }
    }
	
	@Override
 	public void encodeEnd(FacesContext ctx) throws IOException
 	{
		ResponseWriter writer = ctx.getResponseWriter();
		
		writer.startElement("div",this);
		writer.writeAttribute("id",chartId,null);
		if(Objects.isNull(height)) {writer.writeAttribute("class","e-chart",null);}
		else {writer.writeAttribute("style","height:"+height+"px",null);}
		writer.endElement("div");
		
		writer.startElement("script", this);
		
		EchartProvider echart = EchartProvider.instance(writer);
		
		if(Objects.nonNull(type))
		{
			if(Objects.nonNull(scope) && scope.equals("demo")) {echart.demo(type,chartId);}
			else
			{
				switch(JsonEchartFactory.Type.valueOf(type))
				{
					case heatbar: JsonEchartHeatbarFactory.instance(writer).id(chartId).jsf(chartId,grid,data); break;
					default: logger.warn("NYI"); break;
				}
			}
		}
		
		writer.endElement("script");
        writer.writeText(System.getProperty("line.separator"), null);
 	}
}