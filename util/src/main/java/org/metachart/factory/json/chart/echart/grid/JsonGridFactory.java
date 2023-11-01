package org.metachart.factory.json.chart.echart.grid;

import org.metachart.model.json.chart.echart.grid.JsonGrid;

public class JsonGridFactory
{	
	private JsonGrid json;
	
	public static JsonGridFactory instance() {return new JsonGridFactory();}
	private JsonGridFactory()
	{
		json = JsonGridFactory.create();
	}
	 
	public static JsonGrid create() {return new JsonGrid();}
	
	public JsonGridFactory show(Boolean show) {json.setShow(show); return this;}
	public JsonGridFactory maring(int top, int left, int right, int bottom) {json.setTop(""+top); json.setLeft(""+left); json.setRight(""+right); json.setBottom(""+bottom); return this;}
	
	public JsonGrid build() {return json;}
}