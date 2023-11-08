package org.metachart.factory.json.chart.echart.type;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.exlp.util.io.JsUtil;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonEdgeFactory;
import org.metachart.factory.json.chart.echart.data.JsonLinkFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.grid.JsonGridFactory;
import org.metachart.factory.json.chart.echart.grid.JsonSplitAreaFactory;
import org.metachart.factory.json.chart.echart.ui.JsonTooltipFactory;
import org.metachart.factory.json.chart.echart.ui.JsonVisualMapFactory;
import org.metachart.factory.json.function.TxtEchartFunctionFactory;
import org.metachart.interfaces.chart.Data;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonEdge;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.metachart.model.json.chart.echart.grid.JsonGrid;
import org.metachart.model.json.chart.echart.grid.JsonSplitArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonEchartGraphFactory
{
	final static Logger logger = LoggerFactory.getLogger(JsonEchartGraphFactory.class);
	
	private final Writer w;
	private String id; public JsonEchartGraphFactory id(String id) {this.id=id; return this;}
	
	public static JsonEchartGraphFactory instance() {return new JsonEchartGraphFactory(null);}
	public static JsonEchartGraphFactory instance(Writer w) {return new JsonEchartGraphFactory(w);}
	private JsonEchartGraphFactory(Writer w)
	{
		this.w=w;
		id="";
	}
	
	public void jsf(String div, JsonGrid jsfGrid, Data data) throws IOException
	{		
		JsonEchartFactory txtChart = JsonEchartFactory.instance(w,JsonUtil.instance()).id(div);
		
		txtChart.declare(div,JsonHtmlFactory.build("canvas",false));
		txtChart.letData().letCategories("node");
		txtChart.categories("node",this.demoCategories());
		txtChart.option(this.jsfOption(jsfGrid,data));
		
		txtChart.init();
	}
	
	private JsonOption jsfOption(JsonGrid jsfGrid, Data data)
	{
		if(Objects.nonNull(jsfGrid.getHeight()))
		{
			Double d = Double.valueOf(jsfGrid.getHeight());
			jsfGrid.setWidth(""+d*data.getValue().getDoubles1().length);
		}
		
		JsonOption option = new JsonOption();
		
		option.setGrid(JsonGridFactory.instance().size(jsfGrid).margin(0,0,0,0).build());
		
		JsonSplitArea splitArea = JsonSplitAreaFactory.instance().show(true).build();
		option.setAxisX(JsonAxisFactory.instance().show(false).type("category").data("xCategories"+id).splitArea(splitArea).build());
		option.setAxisY(JsonAxisFactory.instance().show(false).type("category").data("yCategories"+id).splitArea(splitArea).build());
		option.setVisualMap(JsonVisualMapFactory.instance().show(false).minMax(0,10).build());
		option.setTooltip(JsonTooltipFactory.instance().position("top").build());
		
		option.setSeries(new ArrayList<>());
		JsonSeries series = new JsonSeries();
		series.setData(JsUtil.magicField("data"+id));
		series.setType(JsonEchartFactory.Type.heatmap.toString());
		
		option.getSeries().add(series);
		return option;
	}

	
	// Demo Methods
	public static void demoChart(JsonEchartFactory fEchart) throws IOException
	{
		JsonEchartGraphFactory f = JsonEchartGraphFactory.instance();
		
		fEchart.letCategories("Node").letData().letEdges();
		fEchart.categories("Node",f.demoCategories());
		fEchart.data(f.demoData().getData());
		fEchart.edges(f.demoEdges());
		fEchart.option(f.demoOption());
	}
	public JsonOption demoOption()
	{
		JsonOption option = new JsonOption();
			
		option.setTooltip(JsonTooltipFactory.instance().position("top").build());
		
		option.setSeries(new ArrayList<>());
		
		JsonSeries series = new JsonSeries();
		series.setType(JsonEchartFactory.Type.graph.toString());
		series.setLayout("force");
		series.setAnimation(true);
		series.setDraggable(true);
		series.setCategories(JsUtil.magicField("categoriesNode"+id));
		series.setData(JsUtil.magicField("data"+id));
		series.setEdges(JsUtil.magicField("edges"+id));
		
		
		option.getSeries().add(series);
		return option;
	}
	public List<JsonData> demoCategories()
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		
		jf.data(JsonDataFactory.instance().name("Cat A").build());
		jf.data(JsonDataFactory.instance().name("Cat B").build());
		
		return jf.build().getData();
	}
	private JsonData demoData()
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		jf.data(JsonDataFactory.instance().name("Node 1").category(0).build());
		jf.data(JsonDataFactory.instance().name("Node 2").category(1).build());
		jf.data(JsonDataFactory.instance().name("Node 3").category(1).build());
		jf.data(JsonDataFactory.instance().name("Node 4").category(1).build());
		return jf.build();
	}
	public List<JsonEdge> demoEdges()
	{
		JsonEdgeFactory jf = JsonEdgeFactory.instance();
		jf.edge(JsonEdgeFactory.edge(0,1));
		jf.edge(JsonEdgeFactory.edge(0,2));
		jf.edge(JsonEdgeFactory.edge(0,3));
		jf.edge(JsonEdgeFactory.edge(0,4));
		jf.edge(JsonEdgeFactory.edge(2,3));
		return jf.build().getEdges();
	}
}