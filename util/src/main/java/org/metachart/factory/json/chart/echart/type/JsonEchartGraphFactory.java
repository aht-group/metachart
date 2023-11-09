package org.metachart.factory.json.chart.echart.type;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Objects;

import org.exlp.util.io.JsUtil;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.demo.JsonEchartDemoGraphFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.grid.JsonGridFactory;
import org.metachart.factory.json.chart.echart.grid.JsonSplitAreaFactory;
import org.metachart.factory.json.chart.echart.ui.JsonTooltipFactory;
import org.metachart.factory.json.chart.echart.ui.JsonVisualMapFactory;
import org.metachart.interfaces.chart.Data;
import org.metachart.model.json.chart.echart.JsonOption;
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
	
	public void jsf(String div, JsonGrid jsfGrid, Data categories, Data data, Data edges) throws IOException
	{		
		JsonEchartFactory f = JsonEchartFactory.instance(w,JsonUtil.instance()).id(div);
		
		JsonEchartDemoGraphFactory demo = JsonEchartDemoGraphFactory.instance().id(id);
		
		f.declare(div,JsonHtmlFactory.build("canvas",false));
		f.categories("Node",categories.getValue().getData());
		f.data(data.getValue().getData());
		f.edges(edges.getValue().getEdges());
		f.option(demo.demoOption());
		
		f.init();
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
}