package org.metachart.factory.json.chart.echart.grid;

import org.metachart.model.json.chart.echart.grid.JsonSplitArea;

public class JsonSplitAreaFactory
{	
	private JsonSplitArea json;


	public static JsonSplitAreaFactory instance() {return new JsonSplitAreaFactory();}
	private JsonSplitAreaFactory()
	{
		json = JsonSplitAreaFactory.create();
	}
	
	public static JsonSplitArea create() {return new JsonSplitArea();}

	
	public JsonSplitAreaFactory show(Boolean show) {json.setShow(show); return this;}
	
	public JsonSplitArea build() {return json;}
}