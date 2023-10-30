package org.metachart.factory.json.chart.echart.type;

import java.util.ArrayList;
import java.util.List;

import org.exlp.util.JsUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonLabelFactory;
import org.metachart.factory.json.chart.echart.JsonLineStyleFactory;
import org.metachart.factory.json.chart.echart.JsonTooltipFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonLinkFactory;
import org.metachart.model.json.chart.echart.JsonData;
import org.metachart.model.json.chart.echart.JsonLink;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.JsonSeries;

public class JsonEchartSankeyFactory
{
	public static JsonEchartSankeyFactory instance() {return new JsonEchartSankeyFactory();}
	private JsonEchartSankeyFactory()
	{
		
	}
	
	public JsonOption demoOption()
	{
		JsonSeries series = new JsonSeries();	
		series.setType(JsonEchartFactory.Type.sankey.toString());
		series.setLineStyle(JsonLineStyleFactory.instance().colorSource().curveness(0.5).build());
		series.setLabel(JsonLabelFactory.instance().colorGrey().fontArial().fontSize(11).build());
		series.setData(JsUtil.magicField("data"));
		series.setLinks(JsUtil.magicField("links"));
		
		JsonOption option = new JsonOption();
		option.setTooltip(JsonTooltipFactory.instance().trigger("item").build());
		option.setSeries(new ArrayList<>());
		option.getSeries().add(series);
		return option;
	}
	
	public List<JsonData> demoData()
	{
		List<JsonData> list = new ArrayList<>();
		
		list.add(JsonDataFactory.build("A"));
		list.add(JsonDataFactory.build("B"));
		list.add(JsonDataFactory.build("C"));
		
		return list;
	}
	public List<JsonLink> demoLinks()
	{
		List<JsonLink> list = new ArrayList<>();
		
		list.add(JsonLinkFactory.build("A","B",2));
		list.add(JsonLinkFactory.build("A","C",1));
		list.add(JsonLinkFactory.build("B","C",1));
		
		return list;
	}
}