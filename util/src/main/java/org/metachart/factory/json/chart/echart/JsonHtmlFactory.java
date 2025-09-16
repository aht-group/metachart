package org.metachart.factory.json.chart.echart;

import org.metachart.model.json.chart.echart.JsonHtml;

public class JsonHtmlFactory
{
	public enum Renderer {svg,canvas}
	
	
	private JsonHtml json;
	
	public static JsonHtmlFactory instance() {return new JsonHtmlFactory();}
	private JsonHtmlFactory()
	{
		json = JsonHtmlFactory.build();
		json.setRenderer(Renderer.svg.toString());
		json.setUseDirtyRect(true);
	}
	
	
	
	public JsonHtml assemble(){return json;}
	
	public static JsonHtml build(Renderer renderer, Boolean useDirtyRect, String width, String height)
	{
		JsonHtml json = JsonHtmlFactory.build(renderer, useDirtyRect);
		json.setWidth(width);
		json.setHeight(height);
		return json;
	}
	public static JsonHtml build(Renderer renderer, Boolean useDirtyRect)
	{
		JsonHtml json = JsonHtmlFactory.build();
		json.setRenderer(renderer.toString());
		json.setUseDirtyRect(useDirtyRect);
		return json;
	}
	public static JsonHtml build() {return new JsonHtml();}
}