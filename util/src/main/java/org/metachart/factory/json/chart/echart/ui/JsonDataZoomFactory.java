package org.metachart.factory.json.chart.echart.ui;

import org.metachart.model.json.chart.echart.ui.JsonDataZoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonDataZoomFactory
{
	final static Logger logger = LoggerFactory.getLogger(JsonDataZoomFactory.class);
	
	private enum Type{slider}
	
	private JsonDataZoom json;
	
	public static JsonDataZoomFactory instance()
	{
		return new JsonDataZoomFactory();
	}
	private JsonDataZoomFactory()
	{
		json = JsonDataZoomFactory.build();
	}
	
	public JsonDataZoomFactory slider() {json.setType(Type.slider.toString()); return this;}
	
	public JsonDataZoom assemble() {return json;}
	
	public static JsonDataZoom build() {return new JsonDataZoom();}
	

}