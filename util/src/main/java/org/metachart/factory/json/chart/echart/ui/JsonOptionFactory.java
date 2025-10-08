package org.metachart.factory.json.chart.echart.ui;

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
import org.metachart.model.json.chart.echart.grid.JsonAxis;
import org.metachart.model.json.chart.echart.grid.JsonLegend;
import org.metachart.model.json.chart.echart.ui.JsonTooltip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonOptionFactory
{
	final static Logger logger = LoggerFactory.getLogger(JsonOptionFactory.class);
	
	private JsonOption json;
	
	public static JsonOptionFactory instance()
	{
		return new JsonOptionFactory();
	}
	private JsonOptionFactory()
	{
		json = JsonOptionFactory.build();
	}
	
	public JsonOptionFactory time2()
	{
		json.setAxisX(JsonAxisFactory.instance().type("time").assemble());
		json.setAxisY(JsonAxisFactory.instance().type("value").assemble());
		json.setUseUtc(true);
		return this;
	}
	public JsonOptionFactory scatter()
	{
		json.setAxisX(JsonAxisFactory.instance().assemble());
		json.setAxisY(JsonAxisFactory.instance().assemble());
		return this;
	}
	
	public JsonOptionFactory series(JsonSeries series)
	{
		if(Objects.isNull(json.getSeries())) {json.setSeries(new ArrayList<>());}
		json.getSeries().add(series);
		return this;
	}
	public JsonOptionFactory series(JsonData data)
	{
		JsonSeries series = new JsonSeries();
		series.setType(JsonEchartFactory.Type.line.toString());
		series.setData(data.getId());
		series.setSmooth(true);
		if(Objects.nonNull(data.getName())) {series.setName(data.getName());}
		return this.series(series);
	}
	public JsonOptionFactory axisY(JsonAxis axis) {json.setAxisY(axis); return this;}
	public JsonOptionFactory legend(JsonLegend legend) {json.setLegend(legend); return this;}
	public JsonOptionFactory tooltip(JsonTooltip tooltip) {json.setTooltip(tooltip); return this;}
	
	public JsonOption assemble() {return json;}
	
	public static JsonOption build() {return new JsonOption();}
	
	public static JsonOption toMagicDatas(JsonOption option)
	{	//This is used for demo charts
		return JsonOptionFactory.toMagicDatas(null, option);
	}
	public static JsonOption toMagicDatas(String chartId, JsonOption option)
	{	//This is used for JSF charts
		for(JsonSeries s : ListUtils.emptyIfNull(option.getSeries()))
		{
			s.setData(JsUtil.magicField(TxtDataFactory.id(chartId,s.getData())));
		}
		if(Objects.nonNull(option.getAxisX()) && Objects.nonNull(option.getAxisX().getData())) {option.getAxisX().setData(JsUtil.magicField(TxtDataFactory.id(chartId,option.getAxisX().getData())));}
		if(Objects.nonNull(option.getTooltip()) && Objects.nonNull(option.getTooltip().getFormatter())) {option.getTooltip().setFormatter(JsUtil.magicField(TxtDataFactory.id(chartId,option.getTooltip().getFormatter())));}
		return option;
	}
}