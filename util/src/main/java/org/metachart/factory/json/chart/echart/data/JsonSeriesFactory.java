package org.metachart.factory.json.chart.echart.data;

import java.util.ArrayList;
import java.util.Objects;

import org.apache.commons.collections4.ListUtils;
import org.exlp.util.io.JsUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.txt.chart.TxtDataFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
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
	
	public JsonSeriesFactory showSymbol(boolean value) {json.setShowSymbol(value);return this;}
	
	public JsonSeriesFactory clear() {json=JsonSeriesFactory.build() ; return this;}
	public JsonSeries assemble() {return json;}
	
	public static JsonSeries build() {return new JsonSeries();}

}