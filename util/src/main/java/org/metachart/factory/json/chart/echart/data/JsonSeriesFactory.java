package org.metachart.factory.json.chart.echart.data;

import java.awt.Color;

import org.metachart.model.json.chart.echart.data.JsonSeries;
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

	public JsonSeriesFactory color(String hex)
	{
		logger.info("Color: "+hex);
		json.setColor(String.format("#%s",hex));
		return this;
	}
	public JsonSeriesFactory color(Color color)
	{
		json.setColor(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
		return this;
	}
	
	public JsonSeriesFactory showSymbol(boolean value) {json.setShowSymbol(value);return this;}
	
	public JsonSeriesFactory clear() {json=JsonSeriesFactory.build() ; return this;}
	public JsonSeries assemble() {return json;}
	
	public static JsonSeries build() {return new JsonSeries();}

}