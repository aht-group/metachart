package org.metachart.factory.json.chart.echart.js.type;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Objects;

import org.exlp.util.io.JsUtil;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.grid.JsonGridFactory;
import org.metachart.factory.json.chart.echart.grid.JsonSplitAreaFactory;
import org.metachart.factory.json.chart.echart.ui.JsonTooltipFactory;
import org.metachart.factory.json.chart.echart.ui.JsonVisualMapFactory;
import org.metachart.factory.json.function.TxtEchartFunctionFactory;
import org.metachart.interfaces.chart.Data;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.metachart.model.json.chart.echart.grid.JsonGrid;
import org.metachart.model.json.chart.echart.grid.JsonSplitArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonEchartHeatbarFactory
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
	
	public void jsf(String div, JsonGrid grid, Data data) throws IOException
	{	
		if(Objects.isNull(grid)) {grid = JsonGridFactory.instance().size("12",null).build();}
		
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(w,JsonUtil.instance()).id(div);

		Double d = Double.valueOf(grid.getHeight());
		jfEchart.declare(div,JsonHtmlFactory.build("svg",true,""+d*data.getValue().getDoubles1().length,grid.getHeight()));
		jfEchart.letData().letCategoriesX().letCategoriesY();
		jfEchart.categories("x",this.xCategories(data));
		jfEchart.categories("y",this.yCategories());
		jfEchart.dataDoubles2(this.toDoubles2(data.getValue()),TxtEchartFunctionFactory.nullify(3));
		jfEchart.option(this.jsfOption(grid,data));
		
		jfEchart.init();
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
	
	public JsonData yCategories() {return JsonDataFactory.instance().string("A").build();}
	public JsonData xCategories(Data data) {return JsonDataFactory.instance().repeat(data.getValue().getDoubles1().length).build();}
	
	public JsonData toDoubles2(JsonData data)
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		for(int i=0;i<data.getDoubles1().length;i++)
		{
			jf.double2(new double[] {i,0,data.getDoubles1()[i]});
		}
		return jf.build();
	}
}