package org.metachart.jsf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UIOutput;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorHolder;
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
public class PivotTable extends UINamingContainer implements ClientBehaviorHolder
{
	final static Logger logger = LoggerFactory.getLogger(PivotTable.class);

	private static enum Attribute {data,locale,saveBean,saveMethod}

	private String data; public String getData() {return data;} public void setData(String data) {this.data = data;}
	private String locale; public String getLocale() {return locale;} public void setLocale(String locale) {this.locale = locale;}

	@Override
	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException
	{
		if(event instanceof PostAddToViewEvent)
		{
			// Include metachart typescript library
			UIOutput js = new UIOutput();
			js.setRendererType("javax.faces.resource.Script");
			js.getAttributes().put("library", "mcTypeScript");
			js.getAttributes().put("name", "dist/pivot.bundle.js");
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
		if (this.isRendered())
		{
		Map<String,Object> map = this.getAttributes();
		this.data = (String) map.get(Attribute.data.toString());
		this.locale = (String) map.get(Attribute.locale.toString());

		ResponseWriter writer = ctx.getResponseWriter();
		writer.startElement("script", this);
		// Prepare the column and row default configurations
		ArrayList<String> columns    = new ArrayList<String>();
		ArrayList<String> rows       = new ArrayList<String>();
        ArrayList<String> renderers  = new ArrayList<String>();

		String            colDef  = "";
		String            rowDef  = "";
		Boolean           hasAgg  = false;
		logger.debug("Pivot Table has " +this.getChildCount() +" Children");
		for (UIComponent child : this.getChildren())
		{
			logger.debug("class " +child.getClass().toString());
			if (child.getClass().getSimpleName().equals("PivotFields"))
			{
				PivotFields field = (PivotFields) child;
				if (field.getCol()) {columns.add(field.getName());logger.debug("Adding " +field.getName() +" to Column definitions");}
				if (field.getRow()) {   rows.add(field.getName());logger.debug("Adding " +field.getName() +" to Row    definitions");}
			}
                        else if (child.getClass().getSimpleName().equals("PivotRenderer"))
			{
				PivotRenderer renderer = (PivotRenderer) child;
				renderers.add(renderer.getType());
			}
                        else if (child.getClass().getSimpleName().equals("PivotAggregator"))
			{
				hasAgg = true;
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
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("     var derivers =     $.pivotUtilities.derivers;");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("     var renderers =    $.extend($.pivotUtilities.renderers, $.pivotUtilities.export_renderers);");
		writer.writeText(System.getProperty("line.separator"), null);

		writer.write("     var tpl =          $.pivotUtilities.aggregatorTemplates;");
		writer.writeText(System.getProperty("line.separator"), null);

		writer.write("     var numberFormat = $.pivotUtilities.numberFormat;");
		writer.writeText(System.getProperty("line.separator"), null);

		//Override default local (default EN number format with DE)
		if(this.locale !=null &&  this.locale.equals("de"))
		{
			writer.write("     var usFmt = numberFormat({");
			writer.writeText(System.getProperty("line.separator"), null);
		    writer.write("       thousandsSep: \".\",");
		    writer.writeText(System.getProperty("line.separator"), null);
		    writer.write("       decimalSep: \",\",");
		    writer.writeText(System.getProperty("line.separator"), null);
		    writer.write("     });");
			writer.writeText(System.getProperty("line.separator"), null);
		}
		else
		{
			writer.write("     var usFmt = numberFormat();");
			writer.writeText(System.getProperty("line.separator"), null);
		}

		writer.write("     var usFmtInt = numberFormat({");
		writer.writeText(System.getProperty("line.separator"), null);
	    writer.write("       digitsAfterDecimal: 0,");
	    writer.writeText(System.getProperty("line.separator"), null);
	  //Override default local (default EN number format with DE)
	    if(this.locale !=null &&  this.locale.equals("de"))
		{
	    	writer.write("       thousandsSep: \".\",");
		    writer.writeText(System.getProperty("line.separator"), null);
		    writer.write("       decimalSep: \",\",");
		    writer.writeText(System.getProperty("line.separator"), null);
		}
	    writer.write("     });");
	    writer.writeText(System.getProperty("line.separator"), null);
	    writer.write("     var usFmtPct = numberFormat({");
	    writer.writeText(System.getProperty("line.separator"), null);
	    writer.write("       digitsAfterDecimal: 1,");
	    writer.writeText(System.getProperty("line.separator"), null);
	    writer.write("       scaler: 100,");
	    writer.writeText(System.getProperty("line.separator"), null);
	    writer.write("       suffix: \"%\",");
	    writer.writeText(System.getProperty("line.separator"), null);

	    //Override default local (default EN number format with DE)
	    if(this.locale !=null &&  this.locale.equals("de"))
		{
	    	writer.write("       thousandsSep: \".\",");
		    writer.writeText(System.getProperty("line.separator"), null);
		    writer.write("       decimalSep: \",\",");
		    writer.writeText(System.getProperty("line.separator"), null);
		}

	    writer.write("     });");
	    writer.writeText(System.getProperty("line.separator"), null);

	    writer.write("$('#output').pivotUI(");
        writer.write("    " +data +",");
        writer.writeText(System.getProperty("line.separator"), null);
        writer.write("    {");
        writer.writeText(System.getProperty("line.separator"), null);


        // Aggregators can be inserted here
        if (hasAgg)
        {
        	writer.write("    aggregators: {");
        		this.encodeChildren(ctx);
        	writer.write("    },");
        }
        writer.writeText(System.getProperty("line.separator"), null);
        // If Renderer definitions are given, add them here
        if (renderers.size()>0)
        {
            	writer.write("    renderers: {");
                    StringBuffer buffer = new StringBuffer();
                    for (String renderer : renderers)
                    {
                        buffer.append("\"" +renderer +"\": renderers['" +renderer +"']");
                        buffer.append(",");
                    }
                    String jsRendererCommand = buffer.toString();
                    jsRendererCommand = jsRendererCommand.substring(0, jsRendererCommand.lastIndexOf(","));
                writer.write(jsRendererCommand);
        	writer.write("},");
                writer.writeText(System.getProperty("line.separator"), null);
        }
        else
        {
            // Add the renderers incl Exporter
            writer.write("    renderers:   renderers," );
            writer.writeText(System.getProperty("line.separator"), null);


            writer.writeText(System.getProperty("line.separator"), null);
        }

        // Columns and row setup need to be done here because they are not standalone renderable
		writer.write("    cols:        [" +colDef +"]," );
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("    rows:        [" +rowDef +"]," );
		writer.writeText(System.getProperty("line.separator"), null);
        writer.write("    });");
        writer.writeText(System.getProperty("line.separator"), null);
        writer.write("  });");
        writer.writeText(System.getProperty("line.separator"), null);

		// Now add some Excel export magic
		writer.write("function exportToTSVFile() {");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("    console.log('Selected ' +this.value);");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("    if (this.value=='TSV Export'){");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("    var data = $('.pvtRendererArea').find('textarea').text();");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("    var blob = new Blob([data], {type: 'data:text/tsv;charset=utf-8'});");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("    var fileName = 'data.tsv';");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("    saveAs(blob, fileName);");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("    console.log('TSV Selected');}");
		writer.writeText(System.getProperty("line.separator"), null);
		writer.write("};");
		writer.writeText(System.getProperty("line.separator"), null);

		// Connect this logic to the selection of the TSV export
		writer.write("$('.pvtRenderer').on('change',exportToTSVFile);");
		writer.writeText(System.getProperty("line.separator"), null);

        writer.endElement("script");
        writer.writeText(System.getProperty("line.separator"), null);
        writer.startElement("div", this);
        writer.writeAttribute("id", this.getClientId(), null);
        writer.write("<div id='output' style='margin: 10px;'></div>");
        writer.write("<div>");
        writer.write("<input type='button' value='Save' id='save' onclick=\"saveRowColumnConfig('" + this.getClientId() + "')\"/>");
        writer.write("</div>");
        writer.endElement("div");

        writer.writeText(System.getProperty("line.separator"), null);
		}
	}

	// --------------------------
	// JSF Decode Phase method
	// --------------------------
	@Override
	public void decode(FacesContext context)
	{
		logger.info("Current Phase: " +context.getCurrentPhaseId().toString());

		if (this.isRendered())
		{
			java.util.Map<String,String> params = context.getExternalContext().getRequestParameterMap();
			String behaviorEvent = params.get("javax.faces.behavior.event");
		    logger.info("Handling event of type: " +behaviorEvent +" in decode phase.");

		    Boolean isPivotSaveEvent     = false;

		    if (null!=behaviorEvent)
		    {
			    isPivotSaveEvent    = behaviorEvent.equals("pivotSave");
		    }

	     // Handling of pivotSave event fired by JavaScript API
	        if (null!= behaviorEvent && isPivotSaveEvent)
	        {
	        	java.util.Map<String, List<ClientBehavior>> behaviors = getClientBehaviors();
	     		if (behaviors.isEmpty())
	     		{
	     			logger.info("no behaviors.exiting.");
	     			return;
	     		}
	            List<ClientBehavior> behaviorsForEvent = behaviors.get(behaviorEvent);

	            if (null != behaviorsForEvent)
	            {
	            	String behaviorSource = params.get("javax.faces.source");
	            	String clientId = getClientId(context);
	            	if (behaviorSource != null && behaviorSource.equals(clientId))
	            	{
	            		for (ClientBehavior behavior: behaviorsForEvent)
	            		{
	            			logger.trace("Found " +behavior.getClass().toString());
	            			PivotAjaxEvent ajaxEvent = new PivotAjaxEvent(this, behavior);
	            			ajaxEvent.setRowAndColumn(params);
	            			behavior.broadcast(ajaxEvent);
	            		}
	            	}
	            }
	        }
		}
	}

	@Override
	public Collection<String> getEventNames()
	{
		ArrayList<String> events = new ArrayList<String>();
		events.add("pivotSave");
		return events;
	}

	@Override
	public String getDefaultEventName() {return "pivotSave";}

	@Override
	public String getFamily() {
		return null;
	}

}