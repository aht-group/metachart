package org.metachart.factory.json.chart.echart.ui;

import org.metachart.model.json.chart.echart.ui.JsonAxisPointer;
import org.metachart.model.json.chart.echart.ui.JsonTooltip;

public class JsonTooltipFactory
{	
	public enum Trigger {axis}
	private JsonTooltip json;
	
	public static JsonTooltipFactory instance() {return new JsonTooltipFactory();}
	private JsonTooltipFactory()
	{
		json = new JsonTooltip();
	}
	
	public JsonTooltipFactory trigger(Trigger trigger) {json.setTrigger(trigger.toString()); return this;}
	public JsonTooltipFactory trigger(String trigger) {json.setTrigger(trigger); return this;}
	public JsonTooltipFactory triggerItem() {json.setTrigger("item"); return this;}
	public JsonTooltipFactory position(String position) {json.setPosition(position); return this;}
	public JsonTooltipFactory axisPointer(JsonAxisPointer axisPointer) {json.setAxisPointer(axisPointer); return this;}
	
	public JsonTooltipFactory formatter(String formatter) {json.setFormatter(formatter); return this;}
	
	public JsonTooltip assemble() {return json;}
}