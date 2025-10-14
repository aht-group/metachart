package org.metachart.factory.json.chart.echart.js.family;

import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

import org.apache.commons.collections4.ListUtils;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.grid.JsonGridFactory;
import org.metachart.factory.json.chart.echart.ui.JsonOptionFactory;
import org.metachart.interfaces.chart.EchartJsFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.metachart.model.json.chart.echart.grid.JsonGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonEchartTimeFactory extends AbstractJsonEchartFactory implements EchartJsFactory
{
	final static Logger logger = LoggerFactory.getLogger(JsonEchartTimeFactory.class);
	
	private final Writer w;
	private String id; public JsonEchartTimeFactory id(String id) {this.id=id; return this;}

	public static JsonEchartTimeFactory instance() {return new JsonEchartTimeFactory(null);}
	public static JsonEchartTimeFactory instance(Writer w) {return new JsonEchartTimeFactory(w);}
	private JsonEchartTimeFactory(Writer w)
	{
		this.w=w;
		id="";
	}
	
	@Override public void json(JsonGrid grid, JsonDatas datas, JsonOption option) throws IOException
	{	
		if(Objects.isNull(grid)) {grid = JsonGridFactory.fallback();}
		
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(w,JsonUtil.instance()).id(id);
		jfEchart.declare(id,JsonHtmlFactory.instance().assemble());
		
		super.let(jfEchart,datas);
		
		for(JsonData d : ListUtils.emptyIfNull(datas.getList()))
		{
			jfEchart.dataTime(d);
		}

		jfEchart.option(JsonOptionFactory.toMagicDatas(id,option));
		jfEchart.init();
	}
}