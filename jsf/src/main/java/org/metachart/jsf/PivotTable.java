package org.metachart.jsf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.PostAddToViewEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.PivotTable")
@ListenerFor(systemEventClass=PostAddToViewEvent.class)
public class PivotTable extends UINamingContainer
{
final static Logger logger = LoggerFactory.getLogger(PivotTable.class);
	
	private static enum Attribute {data}
	
	private String data;
	
	@Override
	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException
	{
		if(event instanceof PostAddToViewEvent)
		{
			// Include JavaScript
			UIOutput js = new UIOutput();
			js.setRendererType("javax.faces.resource.Script");
			js.getAttributes().put("library", "jsMetaChart");
			js.getAttributes().put("name", "pivot.js");
			FacesContext context = this.getFacesContext();
			context.getViewRoot().addComponentResource(context, js, "head");
			
			// Include CSS
	        UIOutput css = new UIOutput();
			css.setRendererType("javax.faces.resource.Stylesheet");
			css.getAttributes().put("library", "cssMetaChart");
			css.getAttributes().put("name", "pivot.css");
			context.getViewRoot().addComponentResource(context, css, "head");
		}
		super.processEvent(event);
	}
	
	@Override
	public void encodeAll(FacesContext ctx) throws IOException
	{
		Map<String,Object> map = this.getAttributes();
		this.data         = (String) map.get(Attribute.data.toString());
		
		ResponseWriter writer = ctx.getResponseWriter();
		writer.startElement("script", this);
		// Prepare the column and row default configurations
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<String> rows    = new ArrayList<String>();
		String            colDef  = "";
		String            rowDef  = "";
		
		logger.info("Pivot Table has " +this.getChildCount() +" Children");
		for (UIComponent child : this.getChildren())
		{
			if (child.getClass().isInstance(PivotFields.class))
			{
				PivotFields field = (PivotFields) child;
				if (field.getCol()) {columns.add(field.getName());logger.info("Adding " +field.getName() +" to Column definitions");}
				if (field.getRow()) {   rows.add(field.getName());logger.info("Adding " +field.getName() +" to Row    definitions");}
			}
		}
		
		if (!columns.isEmpty())
		{
			for (String parameter : columns)
			{
				colDef += "'" +parameter +"',";
			}
			colDef = colDef.substring(0, colDef.lastIndexOf(","));
		}
		
		if (!rows.isEmpty())
		{
			for (String parameter : rows)
			{
				rowDef += "'" +parameter +"',";
			}
			rowDef = rowDef.substring(0, rowDef.lastIndexOf(","));
		}
		
		
		writer.write("$(function(){");
		writer.write("     var derivers =     $.pivotUtilities.derivers;");
		writer.write("     var tpl =          $.pivotUtilities.aggregatorTemplates;");

	    writer.write("$('#output').pivotUI(");
        writer.write("    " +data +","); 
        writer.write("    {");
        
        // Aggregators can be inserted here
        this.encodeChildren(ctx);
        
        // Columns and row setup need to be done here because they are not standalone renderable
		writer.write("    cols: [" +colDef +"]," );
		writer.write("    rows: [" +rowDef +"]," );
        writer.write("    });");
        writer.write("  });");   
        
        writer.endElement("script");
        
        writer.startElement("div", this);
        writer.write("<div id='output' style='margin: 10px;'></div>");
		
	}
	
	public String getData() {return data;}
	public void setData(String data) {this.data = data;}

	@Override
	public String getFamily() {
		return null;
	}
}