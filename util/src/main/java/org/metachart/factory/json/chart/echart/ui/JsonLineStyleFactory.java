package org.metachart.factory.json.chart.echart.ui;

import org.metachart.model.json.chart.echart.ui.JsonLineStyle;

public class JsonLineStyleFactory
{	
	private JsonLineStyle json;
	
	public static JsonLineStyleFactory instance() {return new JsonLineStyleFactory();}
	private JsonLineStyleFactory()
	{
		json = new JsonLineStyle();
	}
	
	public JsonLineStyleFactory colorSource() {json.setColor("source"); return this;}
	public JsonLineStyleFactory curveness(double value) {json.setCurveness(value); return this;}
	
	public JsonLineStyle build() {return json;}
}