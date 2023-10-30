package org.metachart.factory.json.chart.echart;

import org.metachart.model.json.chart.echart.JsonTooltip;

public class JsonTooltipFactory
{	
	private JsonTooltip json;
	public static JsonTooltipFactory instance() {return new JsonTooltipFactory();}
	private JsonTooltipFactory()
	{
		json = new JsonTooltip();
	}
	
	public JsonTooltip build() {return json;}
	
	public JsonTooltipFactory trigger(String trigger)
	{
		json.setTrigger(trigger);
		return this;
	}
}