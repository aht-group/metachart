package org.metachart.factory.json.chart.echart;

import org.exlp.util.JsUtil;
import org.metachart.model.json.chart.echart.JsonAxis;

public class JsonAxisFactory
{	
	private JsonAxis json;
	
	public static JsonAxisFactory instance() {return new JsonAxisFactory();}
	private JsonAxisFactory()
	{
		json = new JsonAxis();
	}
	
	public static JsonAxis create() {return new JsonAxis();}
	
	public JsonAxisFactory type(String type) {json.setType(type); return this;}
	public JsonAxisFactory data(String data) {json.setData(JsUtil.magicField(data)); return this;}
	
	public JsonAxis build() {return json;}
}