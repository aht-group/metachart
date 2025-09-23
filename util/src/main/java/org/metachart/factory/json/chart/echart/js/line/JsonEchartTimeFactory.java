package org.metachart.factory.json.chart.echart.js.line;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Objects;

import org.apache.commons.collections4.ListUtils;
import org.exlp.util.io.JsUtil;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.grid.JsonGridFactory;
import org.metachart.factory.json.chart.echart.ui.JsonOptionFactory;
import org.metachart.factory.txt.chart.TxtDataFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.metachart.model.json.chart.echart.grid.JsonGrid;
import org.metachart.util.provider.data.EchartTimeDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonEchartTimeFactory //implements EchartGraphDataProvider
{
	final static Logger logger = LoggerFactory.getLogger(JsonEchartTimeFactory.class);
	
	private final Writer w;
	private String id; public JsonEchartTimeFactory id(String id) {this.id=id; return this;}
	
//	private JsonData categories; @Override public JsonData getGraphCategories() {return categories;}
//	private JsonData nodes; @Override public JsonData getGraphNodes() {return nodes;}
//	private JsonData edges; @Override public JsonData getGraphEdges() {return edges;}
	
	public static JsonEchartTimeFactory instance() {return new JsonEchartTimeFactory(null);}
	public static JsonEchartTimeFactory instance(Writer w) {return new JsonEchartTimeFactory(w);}
	private JsonEchartTimeFactory(Writer w)
	{
		this.w=w;
		id="";
	}
	
	public void json(JsonGrid grid, JsonDatas datas, JsonOption option) throws IOException
	{	
		if(Objects.isNull(grid)) {grid = JsonGridFactory.fallback();}
		
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(w,JsonUtil.instance()).id(id);
		jfEchart.declare(id,JsonHtmlFactory.instance().assemble());
		
		for(JsonData d : ListUtils.emptyIfNull(datas.getList()))
		{
			jfEchart.letData(d.getId());
		}
		
		for(JsonData d : ListUtils.emptyIfNull(datas.getList()))
		{
			jfEchart.dataTime(d);
		}

		jfEchart.option(JsonOptionFactory.toMagicDatas(id,option));
		jfEchart.init();
	}
	
	public void jsf(JsonGrid grid, EchartTimeDataProvider provider) throws IOException
	{	
		if(Objects.isNull(grid)) {grid = JsonGridFactory.fallback();}
		
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(w,JsonUtil.instance()).id(id);
		jfEchart.declare(id,JsonHtmlFactory.instance().assemble());
		
		for(JsonData d : ListUtils.emptyIfNull(provider.getDatas()))
		{
			jfEchart.letData(d.getId());
		}
		
		for(JsonData d : ListUtils.emptyIfNull(provider.getDatas()))
		{
			jfEchart.dataTime(d);
		}

		jfEchart.option(this.toOption(provider));

		jfEchart.init();
	}
	
	private JsonOption toOption(EchartTimeDataProvider provider)
	{
		JsonOption option = new JsonOption();
		option.setAxisX(JsonAxisFactory.instance().type("time").assemble());
		option.setAxisY(JsonAxisFactory.instance().type("value").assemble());
		
		option.setSeries(new ArrayList<>());
		for(JsonData d : ListUtils.emptyIfNull(provider.getDatas()))
		{
			JsonSeries series = new JsonSeries();
			series.setType(JsonEchartFactory.Type.line.toString());
			series.setData(JsUtil.magicField(TxtDataFactory.dataId(id,d.getId())));
			option.getSeries().add(series);
		}
		
		return option;
	}
}