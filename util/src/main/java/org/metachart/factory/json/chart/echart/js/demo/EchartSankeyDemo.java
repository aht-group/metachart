package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.exlp.util.io.JsUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonLinkFactory;
import org.metachart.factory.json.chart.echart.ui.JsonEmphasisFactory;
import org.metachart.factory.json.chart.echart.ui.JsonEmphasisFactory.Focus;
import org.metachart.factory.json.chart.echart.ui.JsonLabelFactory;
import org.metachart.factory.json.chart.echart.ui.JsonLineStyleFactory;
import org.metachart.factory.json.chart.echart.ui.JsonTooltipFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonLink;
import org.metachart.model.json.chart.echart.data.JsonSeries;

public class EchartSankeyDemo
{
	private final JsonEchartFactory fEchart;
	
	public static EchartSankeyDemo instance(JsonEchartFactory fEchart) {return new EchartSankeyDemo(fEchart);}
	private EchartSankeyDemo(JsonEchartFactory fEchart)
	{
		this.fEchart=fEchart;
	}
	
	public void demo() throws IOException
	{
		
		
		fEchart.letData().letLinks();
		fEchart.data(this.demoData());
		fEchart.links(this.demoLinks());
		fEchart.option(this.demoOption());
	}
	
	public JsonOption demoOption()
	{
		JsonSeries series = new JsonSeries();	
		series.setType(JsonEchartFactory.Type.sankey.toString());
		series.setLineStyle(JsonLineStyleFactory.instance().colorSource().curveness(0.5).build());
		series.setLabel(JsonLabelFactory.instance().colorGrey().fontArial().fontSize(11).build());
		series.setEmphasis(JsonEmphasisFactory.instance().focus(Focus.adjacency).build());
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