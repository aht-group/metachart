package org.metachart.jsf;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.PostAddToViewEvent;
import net.sf.exlp.util.io.JsonUtil;
import org.metachart.model.json.pivot.PivotSettings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.Pivot")
@ListenerFor(systemEventClass=PostAddToViewEvent.class)
public class Pivot extends UINamingContainer
{
final static Logger logger = LoggerFactory.getLogger(Pivot.class);
	
	private static enum Attribute {data, container}
	
	private String data;
	private PivotSettings container;
	
	@Override
	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException
	{
		if(event instanceof PostAddToViewEvent)
		{
			// Include DHTMLX Pivot.js JavaScript
			UIOutput js = new UIOutput();
			js.setRendererType("javax.faces.resource.Script");
			js.getAttributes().put("library", "jsDhtmlx");
			js.getAttributes().put("name", "dhtmlxPivot.js");
			FacesContext context = this.getFacesContext();
			context.getViewRoot().addComponentResource(context, js, "head");
			
			// Include CSS
	        UIOutput css = new UIOutput();
			css.setRendererType("javax.faces.resource.Stylesheet");
			css.getAttributes().put("library", "cssDhtmlx");
			css.getAttributes().put("name", "dhtmlxPivot.css");
			context.getViewRoot().addComponentResource(context, css, "head");
		}
		super.processEvent(event);
	}
	
	@Override
	public void encodeAll(FacesContext ctx) throws IOException
	{
		Map<String,Object> map = this.getAttributes();
		this.data         = (String) map.get(Attribute.data.toString());
		this.container    = (PivotSettings) map.get(Attribute.container.toString());
		
		ResponseWriter writer = ctx.getResponseWriter();
        writer.write("<div id='pivotOutput' style='margin: 10px; height: 700px'></div>");
		writer.startElement("script", this);
		
		writer.write("var dataset = " +data +";");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.writeText(System.getProperty("line.separator"), null);
		
		writer.write("var myPivot = new dhx.Pivot(\"pivotOutput\", {");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("     data: dataset,");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("     fields: ");
		writer.write(JsonUtil.toPrettyString(container.getFields()).replace("\"map\" : {", "\"aliases\" :{ "));
		
		writer.write("     ,");
		writer.writeText(System.getProperty("line.separator"), null);
		
		writer.write("     fieldList:  ");
		writer.writeText(JsonUtil.toPrettyString(container.getFieldList()).replace("\"map\" : {", "\"aliases\" :{ "), null);
		
		writer.write("     ,");
		writer.writeText(System.getProperty("line.separator"), null);
	    
		writer.writeText(System.getProperty("line.separator"), null);
	    writer.write("     layout: {");
	    writer.writeText(System.getProperty("line.separator"), null);
        writer.write("      columnsWidth:\"auto\",                rowsHeadersWidth: \"auto\"        }");
	    writer.writeText(System.getProperty("line.separator"), null);
         
        writer.write("     });");
	    writer.writeText(System.getProperty("line.separator"), null);
		
		writer.write("function runExport(url) {");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("	myPivot.export({");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("		url: url");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("	});");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("};");
        writer.writeText(System.getProperty("line.separator"), null);
		writer.endElement("script");
        writer.writeText(System.getProperty("line.separator"), null);
        
	}
	
	public String getData() {return data;}
	public void setData(String data) {this.data = data;}

	@Override
	public String getFamily() {
		return null;
	}
}