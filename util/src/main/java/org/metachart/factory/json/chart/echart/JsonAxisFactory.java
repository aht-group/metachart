package org.metachart.factory.json.chart.echart;

import org.metachart.model.json.chart.echart.JsonAxis;

public class JsonAxisFactory
{	
	public static JsonAxis build() {return new JsonAxis();}
	public static JsonAxis build(String type)
	{
		JsonAxis json = JsonAxisFactory.build();
		json.setType(type);
		return json;
	}
}