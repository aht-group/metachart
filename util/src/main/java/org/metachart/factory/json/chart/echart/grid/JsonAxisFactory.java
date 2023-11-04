package org.metachart.factory.json.chart.echart.grid;

import org.exlp.util.io.JsUtil;
import org.metachart.model.json.chart.echart.grid.JsonAxis;
import org.metachart.model.json.chart.echart.grid.JsonSplitArea;

public class JsonAxisFactory
{	
	private JsonAxis json;
	
	public static JsonAxisFactory instance() {return new JsonAxisFactory();}
	private JsonAxisFactory()
	{
		json = JsonAxisFactory.create();
	}
	 
	public static JsonAxis create() {return new JsonAxis();}
	
	public JsonAxisFactory show(Boolean show) {json.setShow(show); return this;}
	public JsonAxisFactory type(String type) {json.setType(type); return this;}
	public JsonAxisFactory data(String data) {json.setData(JsUtil.magicField(data)); return this;}
	public JsonAxisFactory splitArea(JsonSplitArea splitArea) {json.setSplitArea(splitArea); return this;}
	
	public JsonAxis build() {return json;}
}