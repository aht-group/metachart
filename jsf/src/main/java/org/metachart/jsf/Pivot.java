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

import org.metachart.model.json.pivot.PivotContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.io.JsonUtil;

@FacesComponent(value="org.metachart.jsf.Pivot")
@ListenerFor(systemEventClass=PostAddToViewEvent.class)
public class Pivot extends UINamingContainer
{
final static Logger logger = LoggerFactory.getLogger(Pivot.class);
	
	private static enum Attribute {data,container}
	
	private final static String LS = System.getProperty("line.separator");
	
	private String data; public String getData() {return data;} public void setData(String data) {this.data = data;}
	
	@Override
	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException
	{
		if(event instanceof PostAddToViewEvent)
		{
			// Include DHTMLX Pivot.js JavaScript
			UIOutput js = new UIOutput();
			js.setRendererType("javax.faces.resource.Script");
			js.getAttributes().put("library", "jsMetaChart");
			js.getAttributes().put("name", "dhtmlxPivot.js");
			FacesContext context = this.getFacesContext();
			context.getViewRoot().addComponentResource(context, js, "head");
			
			// Include CSS
	        UIOutput css = new UIOutput();
			css.setRendererType("javax.faces.resource.Stylesheet");
			css.getAttributes().put("library", "cssMetaChart");
			css.getAttributes().put("name", "dhtmlxPivot.css");
			context.getViewRoot().addComponentResource(context, css, "head");
		}
		super.processEvent(event);
	}
	
	@Override public String getFamily() {return null;}
	
	@Override
	public void encodeAll(FacesContext ctx) throws IOException
	{
		Map<String,Object> map = this.getAttributes();
		this.data         = (String) map.get(Attribute.data.toString());
		
		PivotContainer container = (PivotContainer) map.get(Attribute.container.toString());
		JsonUtil.info(container);
				
		ResponseWriter writer = ctx.getResponseWriter();
        writer.write("<div id='pivotOutput' style='margin: 10px;'></div>");
		writer.startElement("script", this);
		
		writer.write("var dataset = " +data +";");
		writer.writeText(LS, null);
		
		writer.write("var myPivot = new dhx.Pivot(\"pivotOutput\", {");
		writer.writeText(LS, null);
		writer.write("     data: dataset,"+LS);
		writer.write("     fields: "+JsonUtil.toString(container.getFields())+","+LS);
		writer.write("     fieldList: "+JsonUtil.toString(container.getFieldList())+LS);
	    writer.write("     });");
	    writer.writeText(LS, null);
        
        writer.endElement("script");
        writer.writeText(LS, null);
	}	
}