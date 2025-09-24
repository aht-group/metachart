package org.metachart.factory.json.chart.echart.grid;

import org.metachart.model.json.chart.echart.grid.JsonAxis;
import org.metachart.model.json.chart.echart.grid.JsonLegend;

public class JsonLegendFactory
{	
	private JsonLegend json;
	
	public static JsonLegendFactory instance() {return new JsonLegendFactory();}
	private JsonLegendFactory()
	{
		json = JsonLegendFactory.build();
	}
	
	public JsonLegendFactory show(Boolean show) {json.setShow(show); return this;}
	public JsonLegendFactory oHoritontal() {return this.orient("horizontal");}
	public JsonLegendFactory oVertical() {return this.orient("vertical");}
	public JsonLegendFactory orient(String value) {json.setOrient(value); return this;}
	public JsonLegend assemble() {return json;}
	
	public static JsonLegend build() {return new JsonLegend();}
}