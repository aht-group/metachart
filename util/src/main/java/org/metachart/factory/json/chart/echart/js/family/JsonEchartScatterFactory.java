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
import org.metachart.model.json.chart.echart.JsonEchart;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.metachart.model.json.chart.echart.grid.JsonGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonEchartScatterFactory extends AbstractJsonEchartFactory //implements EchartJsFactory
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
	
	public void js(JsonEchart chart) throws IOException
	{
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(w,JsonUtil.instance()).id(id);
		jfEchart.declare(id,JsonHtmlFactory.instance().assemble());
		
		super.let(jfEchart,chart.getDatas());
		
		for(JsonData d : ListUtils.emptyIfNull(chart.getDatas()))
		{
			switch(JsonDataFactory.Type.valueOf(d.getMcType()))
			{
				case data:  jfEchart.dataDoubles2(d); break;
				case dates:  jfEchart.dataDate1(d); break;
			}
		}
		

		jfEchart.option(JsonOptionFactory.toMagicDatas(id,chart.getOption()));
		jfEchart.init();
	}
	
	
//	@Override
	@Deprecated
	public void json(JsonGrid grid, JsonDatas datas, JsonOption option) throws IOException
	{	
		if(Objects.isNull(grid)) {grid = JsonGridFactory.fallback();}
		
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(w,JsonUtil.instance()).id(id);
		jfEchart.declare(id,JsonHtmlFactory.instance().assemble());
		
		super.let(jfEchart,datas);
		
		for(JsonData d : ListUtils.emptyIfNull(datas.getList()))
		{
			switch(JsonDataFactory.Type.valueOf(d.getMcType()))
			{
				case data:  jfEchart.dataDoubles2(d); break;
				case dates:  jfEchart.dataDate1(d); break;
			}
		}

		jfEchart.option(JsonOptionFactory.toMagicDatas(id,option));
		jfEchart.init();
	}
	
	public static String functionTooltipDate(String data)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("function scatterTooltipFormatter(params) {");
		sb.append("const index = params.dataIndex;");
		sb.append("const datum = ").append(data).append("[index];");
		sb.append(" return `${datum}`}");
		return sb.toString();
	}
}