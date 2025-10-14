package org.metachart.factory.json.chart.echart.js.family;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Objects;

import org.apache.commons.lang3.ObjectUtils;
import org.exlp.util.io.JsUtil;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory.Type;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.grid.JsonGridFactory;
import org.metachart.factory.json.chart.echart.grid.JsonSplitAreaFactory;
import org.metachart.factory.json.chart.echart.ui.JsonOptionFactory;
import org.metachart.factory.json.chart.echart.ui.JsonTooltipFactory;
import org.metachart.factory.json.chart.echart.ui.JsonVisualMapFactory;
import org.metachart.factory.json.function.TxtEchartFunctionFactory;
import org.metachart.interfaces.chart.Data;
import org.metachart.interfaces.chart.EchartJsFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.metachart.model.json.chart.echart.grid.JsonGrid;
import org.metachart.model.json.chart.echart.grid.JsonSplitArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonEchartHeatbarFactory extends AbstractJsonEchartFactory implements EchartJsFactory
{
	final static Logger logger = LoggerFactory.getLogger(JsonEchartHeatbarFactory.class);
	
	private final Writer w;
	private String id; public JsonEchartHeatbarFactory id(String id) {this.id=id; return this;}
	
	public static JsonEchartHeatbarFactory instance() {return new JsonEchartHeatbarFactory(null);}
	public static JsonEchartHeatbarFactory instance(Writer w) {return new JsonEchartHeatbarFactory(w);}
	private JsonEchartHeatbarFactory(Writer w)
	{
		this.w=w;
		id="";
	}
	
	@Override public void json(JsonGrid grid, JsonDatas datas, JsonOption option) throws IOException
	{	
		if(Objects.isNull(grid)) {grid = JsonGridFactory.instance().size("12",null).assemble();}
		
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(w,JsonUtil.instance()).id(id);
		jfEchart.declare(id,JsonHtmlFactory.instance().assemble());
		
		super.let(jfEchart,datas);
		jfEchart.letCategory("X");
		jfEchart.letCategory("Y");
		
		JsonData data = datas.getList().get(0);
		jfEchart.category("X",JsonEchartHeatbarFactory.categoryX(data.getDoubles1().length));
		jfEchart.category("Y",JsonEchartHeatbarFactory.categoryY());
		jfEchart.dataDoubles2(JsonEchartHeatbarFactory.toDoubles2(data),TxtEchartFunctionFactory.nullify(3));
		
		JsonUtil.info(datas);
//		for(JsonData d : ListUtils.emptyIfNull(datas.getList()))
//		{
//			switch(JsonDataFactory.Type.valueOf(d.getMcType()))
//			{
//				case data:  jfEchart.dataDoubles2(d); break;
//				case dates:  jfEchart.dataDate1(d); break;
//				default: logger.warn(d.getMcType()+" should not be used here!");
//			}
//		}

		jfEchart.option(JsonOptionFactory.toMagicDatas(id,option));
		jfEchart.init();
	}
	
	public static JsonData categoryX(int size)
	{
		JsonDataFactory jf = JsonDataFactory.instance().id("X").type(Type.category);
		for (int i=0;i<size;i++)
		{
			jf.string(""+i);
        }
		return jf.assemble();
	}
	
	public static JsonData categoryY() {return JsonDataFactory.instance().id("Y").type(Type.category).string("A").assemble();}
	
	public JsonData xCategories(Data data)
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		if(Objects.nonNull(data.getValue())) {jf.repeat(data.getValue().getDoubles1().length);}
		else {jf.repeat(0);}
		return jf.assemble();
	}
	
	public static JsonData toDoubles2(JsonData data)
	{
		JsonDataFactory jf = JsonDataFactory.instance().id(data.getId()).type(Type.data);
		if(Objects.nonNull(data) && ObjectUtils.isNotEmpty(data.getDoubles1()))
		{
			for(int i=0;i<data.getDoubles1().length;i++)
			{
				jf.double2(new double[] {i,0,data.getDoubles1()[i]});
			}
		}
		return jf.assemble();
	}
}