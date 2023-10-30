package org.metachart.factory.json.chart.echart.data;

import org.metachart.model.json.chart.echart.JsonData;

public class JsonDataFactory
{	
	public static JsonData create() {return new JsonData();}
	
	public static JsonData build(String name)
	{
		JsonData json = JsonDataFactory.create();
		json.setName(name);
		return json;
	}
	
}