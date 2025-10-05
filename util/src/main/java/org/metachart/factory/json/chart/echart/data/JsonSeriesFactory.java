package org.metachart.factory.json.chart.echart.data;

import java.awt.Color;

import org.exlp.util.io.JsUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.txt.chart.TxtDataFactory;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.metachart.model.json.chart.echart.grid.JsonMarkArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonSeriesFactory
{
	final static Logger logger = LoggerFactory.getLogger(JsonSeriesFactory.class);
	
	private JsonSeries json;
	
	public static JsonSeriesFactory instance()
	{
		return new JsonSeriesFactory();
	}
	private JsonSeriesFactory()
	{
		json = JsonSeriesFactory.build();
	}
	
	public JsonSeriesFactory clear() {json=JsonSeriesFactory.build() ; return this;}

	public JsonSeriesFactory type(JsonEchartFactory.Type type) {json.setType(type.toString());return this;}
	public JsonSeriesFactory color(String hex){json.setColor(String.format("#%s",hex));return this;}
	public JsonSeriesFactory color(Color color) {json.setColor(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));return this;}
	public JsonSeriesFactory showSymbol(boolean value) {json.setShowSymbol(value);return this;}
	public JsonSeriesFactory data(String name) {json.setData(TxtDataFactory.dataId(name)); return this;}
	public JsonSeriesFactory markArea(JsonMarkArea area) {json.setMarkArea(area); return this;}
	
	public JsonSeries assemble() {return json;}
	
	public static JsonSeries build() {return new JsonSeries();}

}