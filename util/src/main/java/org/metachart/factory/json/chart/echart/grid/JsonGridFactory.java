package org.metachart.factory.json.chart.echart.grid;

import org.metachart.model.json.chart.echart.grid.JsonGrid;

public class JsonGridFactory
{	
	private JsonGrid json;
	
	public static JsonGridFactory instance() {return new JsonGridFactory();}
	private JsonGridFactory()
	{
		json = JsonGridFactory.build();
	}
	 
	public static JsonGrid build() {return new JsonGrid();}
	
	public JsonGridFactory show(Boolean show) {json.setShow(show); return this;}
	public JsonGridFactory margin(int top, int left, int right, int bottom) {json.setTop(""+top); json.setLeft(""+left); json.setRight(""+right); json.setBottom(""+bottom); return this;}
	public JsonGridFactory size(String height, String width) {json.setHeight(height); json.setWidth(width); return this;}
	public JsonGridFactory size(int height, int width) {json.setHeight(""+height); json.setWidth(""+width); return this;}
	public JsonGridFactory size(JsonGrid grid) {json.setHeight(grid.getHeight()); json.setWidth(grid.getWidth()); return this;}
	
	public JsonGrid assemble() {return json;}
}