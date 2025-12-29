package org.metachart.jsf.chart.orgchart;

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
import org.exlp.util.io.JsonUtil;
import org.exlp.util.jx.ComponentAttribute;
import org.exlp.util.jx.JsfUtil;
import org.metachart.factory.json.chart.EchartProvider;
import org.metachart.factory.json.chart.echart.data.JsonDatasFactory;
import org.metachart.factory.json.chart.echart.grid.JsonGridFactory;
import org.metachart.factory.json.chart.echart.js.family.JsonEchartCategoryFactory;
import org.metachart.factory.json.chart.echart.js.family.JsonEchartHeatbarFactory;
import org.metachart.factory.json.chart.echart.js.family.JsonEchartScatterFactory;
import org.metachart.factory.json.chart.echart.js.family.JsonEchartTimeFactory;
import org.metachart.factory.json.chart.echart.js.graph.JsonEchartGraphFactory;
import org.metachart.factory.json.chart.orgchart.JsonOrgchartFactory;
import org.metachart.jsf.common.Data;
import org.metachart.jsf.common.Title;
import org.metachart.model.json.chart.echart.JsonEchart;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.metachart.model.json.chart.echart.grid.JsonGrid;
import org.metachart.model.json.chart.orgchart.data.JsonOptions;
import org.metachart.util.provider.data.EchartCategoryDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.chart.orgchart.Chart")
@ListenerFor(systemEventClass=PostAddToViewEvent.class)
public class Chart extends UINamingContainer
{
	final static Logger logger = LoggerFactory.getLogger(Chart.class);

//	private String type; public String getType() {return type;} public void setType(String type) {this.type = type;}
//	private String scope; public String getScope() {return scope;} public void setScope(String scope) {this.scope = scope;}

//	private String height; public String getHeight() {return height;} public void setHeight(String height) {this.height = height;}
	
//	private JsonOption option; public JsonOption getOption() {return option;} public void setOption(JsonOption option) {this.option = option;}

	private static enum Attribute {options}

	public Chart()
	{
		logger.info("New "+this.toString());
	}
	
	@Override
	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException
	{
		super.processEvent(event);
		JsfUtil.pushJsToHead(this.getFacesContext(), "mcTypeScript", "dist/mc.bundle.js");
	}
	
	@Override
 	public void encodeBegin(FacesContext ctx) throws IOException
 	{
//		System.out.println();
		super.encodeBegin(ctx);
		logger.trace("encodeBegin "+this.getClientId());
		
		options = ComponentAttribute.toObject(ctx, this, Attribute.options, null);
		
//		type = ComponentAttribute.toString(ctx,this,Attribute.type,type);
//		scope = ComponentAttribute.toString(ctx,this,Attribute.scope,scope);
//		height = ComponentAttribute.toString(ctx,this,Attribute.height,height);
//		option = ComponentAttribute.toObject(ctx, this, Attribute.option,null);
		
//		logger.info(StringUtil.stars());
//		logger.info("ID:"+super.getId());
//		logger.info("ClientID:"+super.getClientId());
//		logger.info("Parent.ID:"+super.getParent().getId());
//		logger.info("Parent.ClientID:"+super.getParent().getClientId());
 	}
	
//	private Title title;
	
	private JsonOptions options;
//	private Data data;
//	private Data categories;
//	private Data edges;
	
	@Override public boolean getRendersChildren() {return true;}
	@Override public void encodeChildren(FacesContext ctx) throws IOException
	{
	    logger.trace("encodeChildren "+this.getClientId());
	    
//	    data=null;
//	    categories=null;
	    
	    super.encodeChildren(ctx);
		
//	    JsonDatasFactory jfDatas = JsonDatasFactory.instance();
//		for (UIComponent child : this.getChildren())
//        {
//        	logger.trace(child.getClass().getName());
//        	if(child instanceof org.metachart.jsf.common.Title) {title = (org.metachart.jsf.common.Title)child;}
//        	else if(child instanceof org.metachart.jsf.common.Data)
//        	{
//        		org.metachart.jsf.common.Data c = (org.metachart.jsf.common.Data)child;
//        		switch(Data.Type.valueOf(c.getType()))
//        		{
//        			case data:	data=c; jfDatas.add(c.getValue()); break;
//        			case category: categories=c; break;
//        			case edge: edges=c; break;
//        		}
//        	}
//        	else if(child instanceof org.metachart.jsf.common.Datas)
//        	{
//        		org.metachart.jsf.common.Datas c = (org.metachart.jsf.common.Datas)child;
//        		jfDatas.add(c.getValue());
//        	}
//        }
//		
//		datas = jfDatas.assemble();
	}
		
	@Override
 	public void encodeEnd(FacesContext ctx) throws IOException
	{
		ResponseWriter writer = ctx.getResponseWriter();
		
		String chartId = this.getClientId().replace(":","_");

//		logger.trace("type: "+type+" "+this.getClientId());
		
//		JsonGrid grid = null;
//		if(ObjectUtils.anyNotNull(height)) {grid = JsonGridFactory.instance().size(height,null).assemble();}
		
		writer.startElement("div",this);
		writer.writeAttribute("id",chartId,null);
//		if(Objects.isNull(height)) {writer.writeAttribute("class","e-chart",null);}
//		else {writer.writeAttribute("style","height:"+height+"px",null);}
		writer.endElement("div");
		
		writer.startElement("script", this);
		
		JsonOrgchartFactory.instance(writer,JsonUtil.instance()).id(chartId).json();
		
//		writer.write("var options = {");
//		writer.write(	"data: {");
//		writer.write(		"name: 'Lorem',");
//		writer.write(		"title: '1. Level',");
//		writer.write(		"relationship: '001',");
//		writer.write(		"children: [{");
//		writer.write(			"name: 'Ipsum',");
//		writer.write(			"title: '2. Level',");
//		writer.write(			"relationship: '110'");
//		writer.write(		"},{");
//		writer.write(			"name: 'Dolor',");
//		writer.write(			"title: '2. Level',");
//		writer.write(			"relationship: '111',");
//		writer.write(			"children: [{");
//		writer.write(				"name: 'Sit',");
//		writer.write(				"title: '3. Level',");
//		writer.write(				"relationship: '110'");
//		writer.write(			"},{");
//		writer.write(				"name: 'Amet',");
//		writer.write(				"title: '3. Level',");
//		writer.write(				"relationship: '110'");
//		writer.write(			"}]");
//		writer.write(		"}]");
//		writer.write(	"},");
//		writer.write(	"nodeContent: 'title'");
//		writer.write("};");
//		writer.write("$('#" + chartId + "').orgchart(options);");
		
//		if(Objects.nonNull(type))
//		{
//			if(Objects.nonNull(scope) && scope.equals("demo")) {EchartProvider.instance(writer).demo(type,chartId);}
//			else if(Objects.nonNull(option))
//			{
//				switch(JsonEchart.Type.valueOf(type))
//				{
//					case category: 	JsonEchartCategoryFactory.instance(writer).id(chartId).json(grid, datas, option); break;
//					case time: 		JsonEchartTimeFactory.instance(writer).id(chartId).json(grid, datas, option); break;
//					case scatter: 	JsonEchartScatterFactory.instance(writer).id(chartId).json(grid, datas, option); break;
//					case heatbar: 	JsonEchartHeatbarFactory.instance(writer).id(chartId).json(grid, datas, option); break;
//					default: logger.warn("NYI"); break;
//				}
//			}
//			else
//			{
//				switch(JsonEchart.Type.valueOf(type))
//				{
//					case graph: JsonEchartGraphFactory.instance(writer).id(chartId).jsf(chartId,grid,categories,data,edges); break;
//					default: logger.warn("NYI"); break;
//				}
//			}
//		}
		
		writer.endElement("script");
        writer.writeText(System.getProperty("line.separator"), null);
        
        super.encodeEnd(ctx);
        logger.trace("encodeEnd "+this.getClientId());
	}	
}