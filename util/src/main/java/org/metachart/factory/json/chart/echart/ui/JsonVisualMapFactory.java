package org.metachart.factory.json.chart.echart.ui;

import org.metachart.model.json.chart.echart.ui.JsonVisualMap;

public class JsonVisualMapFactory
{	
	public enum Orient{horizontal,vertical};
	
	private JsonVisualMap json;


	public static JsonVisualMapFactory instance() {return new JsonVisualMapFactory();}
	private JsonVisualMapFactory()
	{
		json = new JsonVisualMap();
	}
	
	public static JsonVisualMap create() {return new JsonVisualMap();}

	
	public JsonVisualMapFactory show(Boolean show) {json.setShow(show); return this;}
	public JsonVisualMapFactory minMax(Integer min, Integer max) {json.setMin(min); json.setMax(max); return this;}
	public JsonVisualMapFactory orient(Orient orient) {json.setOrient(orient.toString()); return this;}
	
	public JsonVisualMap build() {return json;}
}