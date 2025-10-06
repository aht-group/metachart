package org.metachart.factory.json.chart.echart.js.family;

import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

import org.apache.commons.collections4.ListUtils;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.grid.JsonGridFactory;
import org.metachart.factory.json.chart.echart.ui.JsonOptionFactory;
import org.metachart.interfaces.chart.EchartJsFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.metachart.model.json.chart.echart.grid.JsonGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonEchartCategoryFactory implements EchartJsFactory
{
	final static Logger logger = LoggerFactory.getLogger(JsonEchartCategoryFactory.class);
	
	private final Writer w;
	private String id; public JsonEchartCategoryFactory id(String id) {this.id=id; return this;}
	
	public static JsonEchartCategoryFactory instance() {return new JsonEchartCategoryFactory(null);}
	public static JsonEchartCategoryFactory instance(Writer w) {return new JsonEchartCategoryFactory(w);}
	private JsonEchartCategoryFactory(Writer w)
	{
		this.w=w;
		id="";
	}
	
	@Override public void json(JsonGrid grid, JsonDatas datas, JsonOption option) throws IOException
	{	
		if(Objects.isNull(grid)) {grid = JsonGridFactory.fallback();}
		
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(w,JsonUtil.instance()).id(id);
		jfEchart.declare(id,JsonHtmlFactory.instance().assemble());
		
		for(JsonData d : ListUtils.emptyIfNull(datas.getList()))
		{
			if(Objects.nonNull(d.getMcType()))
			{
				switch(JsonDataFactory.Type.valueOf(d.getMcType()))
				{
					case category:  jfEchart.letCategory(d.getId()); break;
					case data:  jfEchart.letData(d.getId()); break;
				}
			}
		}
		
		for(JsonData d : ListUtils.emptyIfNull(datas.getList()))
		{
			if(Objects.nonNull(d.getMcType()) && d.getMcType().equals(JsonDataFactory.Type.category.toString()))
			{
				jfEchart.category(d.getId(),d);
			}
		}
		
		for(JsonData d : ListUtils.emptyIfNull(datas.getList()))
		{
			if(Objects.nonNull(d.getMcType()) && d.getMcType().equals(JsonDataFactory.Type.data.toString()))
			{
				jfEchart.dataDoubles1(d);
			}
		}

		jfEchart.option(JsonOptionFactory.toMagicDatas(id,option));
		jfEchart.init();
	}
}