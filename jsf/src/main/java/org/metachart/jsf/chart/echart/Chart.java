package org.metachart.jsf.chart.echart;

import java.io.IOException;
import java.util.Objects;

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
import org.exlp.util.jx.ComponentAttribute;
import org.exlp.util.jx.JsfUtil;
import org.metachart.factory.json.chart.EchartProvider;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.grid.JsonGridFactory;
import org.metachart.factory.json.chart.echart.script.type.JsonEchartGraphFactory;
import org.metachart.factory.json.chart.echart.script.type.JsonEchartHeatbarFactory;
import org.metachart.jsf.common.Data;
import org.metachart.jsf.common.Title;
import org.metachart.model.json.chart.echart.grid.JsonGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.chart.echart.Chart")
@ListenerFor(systemEventClass=PostAddToViewEvent.class)
public class Chart extends UINamingContainer
{
	final static Logger logger = LoggerFactory.getLogger(Chart.class);

	private String type; public String getType() {return type;} public void setType(String type) {this.type = type;}
	private String scope; public String getScope() {return scope;} public void setScope(String scope) {this.scope = scope;}

	private String height; public String getHeight() {return height;} public void setHeight(String height) {this.height = height;}

	private static enum Attribute {scope,type,height}

	public Chart()
	{
		logger.info("New "+this.toString());
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
//		System.out.println();
		super.encodeBegin(ctx);
		logger.trace("encodeBegin "+this.getClientId());
		
		type = ComponentAttribute.toString(ctx,this,Attribute.type,type);
		scope = ComponentAttribute.toString(ctx,this,Attribute.scope,scope);
		height = ComponentAttribute.toString(ctx,this,Attribute.height,height);
		
//		logger.info(StringUtil.stars());
//		logger.info("ID:"+super.getId());
//		logger.info("ClientID:"+super.getClientId());
//		logger.info("Parent.ID:"+super.getParent().getId());
//		logger.info("Parent.ClientID:"+super.getParent().getClientId());
 	}
	
	private Title title;
	private Data data;
	private Data categories;
	private Data edges;
	
	@Override public boolean getRendersChildren() {return true;}
	@Override public void encodeChildren(FacesContext ctx) throws IOException
	{
	    logger.trace("encodeChildren "+this.getClientId());
	    
	    data=null;
	    categories=null;
	    
	    super.encodeChildren(ctx);
		
		for (UIComponent child : this.getChildren())
        {
        	logger.trace(child.getClass().getName());
        	if(child instanceof org.metachart.jsf.common.Title) {title = (org.metachart.jsf.common.Title)child;}
        	else if(child instanceof org.metachart.jsf.common.Data)
        	{
        		org.metachart.jsf.common.Data c = (org.metachart.jsf.common.Data)child;
        		switch(Data.Type.valueOf(c.getType()))
        		{
        			case data:	data=c; break;
        			case category: categories=c; break;
        			case edge: edges=c; break;
        		}
        		
        	}
        }
	}
		
	@Override
 	public void encodeEnd(FacesContext ctx) throws IOException
	{
		ResponseWriter writer = ctx.getResponseWriter();
		
		String chartId = this.getClientId().replace(":","_");

		logger.trace("type: "+type+" "+this.getClientId());
		
		JsonGrid grid = null;
		if(ObjectUtils.anyNotNull(height)) {grid = JsonGridFactory.instance().size(height,null).build();}
		
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
					case graph: JsonEchartGraphFactory.instance(writer).id(chartId).jsf(chartId,grid,categories,data,edges); break;
					default: logger.warn("NYI"); break;
				}
			}
		}
		
		writer.endElement("script");
        writer.writeText(System.getProperty("line.separator"), null);
        
        super.encodeEnd(ctx);
        logger.trace("encodeEnd "+this.getClientId());
	}	
}