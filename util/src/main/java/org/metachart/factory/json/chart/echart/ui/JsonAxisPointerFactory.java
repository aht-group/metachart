package org.metachart.factory.json.chart.echart.ui;

import org.metachart.model.json.chart.echart.ui.JsonAxisPointer;

public class JsonAxisPointerFactory
{	
	public enum Type {cross}
	
	private JsonAxisPointer json;
	
	public static JsonAxisPointerFactory instance() {return new JsonAxisPointerFactory();}
	private JsonAxisPointerFactory()
	{
		json = new JsonAxisPointer();
	}
	
	public JsonAxisPointerFactory typeCross() {json.setType(Type.cross.toString()); return this;}
	
	
	public JsonAxisPointer assemble() {return json;}
}