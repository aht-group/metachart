package org.metachart.factory.json.chart.echart.js.family;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonEchartScatterFactory
{
	final static Logger logger = LoggerFactory.getLogger(JsonEchartScatterFactory.class);
	
	private final Writer w;
	private String id; public JsonEchartScatterFactory id(String id) {this.id=id; return this;}
	
	public static JsonEchartScatterFactory instance() {return new JsonEchartScatterFactory(null);}
	public static JsonEchartScatterFactory instance(Writer w) {return new JsonEchartScatterFactory(w);}
	private JsonEchartScatterFactory(Writer w)
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
			jfEchart.dataDoubles2(d);
		}

		jfEchart.option(JsonOptionFactory.toMagicDatas(id,option));
		jfEchart.init();
	}
	
	public void jsf(JsonGrid grid, JsonDatas datas) throws IOException
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
			jfEchart.dataDoubles2(d);
		}

		jfEchart.option(this.toOption(datas));

		jfEchart.init();
	}
	
	private JsonOption toOption(JsonDatas datas)
	{
		JsonOption option = new JsonOption();
		option.setAxisX(JsonAxisFactory.instance().assemble());
		option.setAxisY(JsonAxisFactory.instance().assemble());
		
		option.setSeries(new ArrayList<>());
		for(JsonData d : ListUtils.emptyIfNull(datas.getList()))
		{
			JsonSeries series = new JsonSeries();
			series.setType(JsonEchartFactory.Type.scatter.toString());
			series.setData(JsUtil.magicField(TxtDataFactory.dataId(id,d.getId())));
			option.getSeries().add(series);
		}
		
		return option;
	}
}