package org.metachart.factory.json.chart.echart.data;

import java.util.Objects;

import org.metachart.model.json.chart.echart.data.JsonLink;

public class JsonLinkFactory
{	
	public static JsonLink create() {return new JsonLink();}
	
	public static JsonLink build(String source, String target, Integer value)
	{
		Double d = null; if(Objects.nonNull(value)) {d = value.doubleValue();}
		return JsonLinkFactory.build(source, target, d);
	}
	public static JsonLink build(String source, String target, Double value)
	{
		JsonLink json = JsonLinkFactory.create();
		json.setSource(source);
		json.setTarget(target);
		json.setValue(value);
		return json;
	}
	
}