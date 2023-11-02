package org.metachart.factory.json.chart.echart.ui;

import org.metachart.model.json.chart.echart.ui.JsonTooltip;

public class JsonTooltipFactory
{	
	private JsonTooltip json;
	
	public static JsonTooltipFactory instance() {return new JsonTooltipFactory();}
	private JsonTooltipFactory()
	{
		json = new JsonTooltip();
	}
	
	public JsonTooltipFactory trigger(String trigger) {json.setTrigger(trigger);return this;}
	public JsonTooltipFactory position(String position) {json.setPosition(position); return this;}
	
	public JsonTooltip build() {return json;}
}