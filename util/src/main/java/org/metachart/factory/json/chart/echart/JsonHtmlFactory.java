package org.metachart.factory.json.chart.echart;

import org.metachart.model.json.chart.echart.JsonHtml;

public class JsonHtmlFactory
{	
	public static JsonHtml build() {return new JsonHtml();}
	public static JsonHtml build(String renderer, Boolean useDirtyRect)
	{
		JsonHtml json = JsonHtmlFactory.build();
		json.setRenderer(renderer);
		json.setUseDirtyRect(useDirtyRect);
		return json;
	}
}