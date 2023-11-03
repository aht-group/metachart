package org.metachart.factory.json.chart.echart.type;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import org.exlp.util.JsUtil;
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

import net.sf.exlp.util.io.JsonUtil;

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
	
	public void jsf(String div, JsonGrid jsfGrid, Data data) throws IOException
	{
		JsonEchartFactory txtChart = JsonEchartFactory.instance(w,JsonUtil.instance()).id(div);
		
		txtChart.declare(div,JsonHtmlFactory.build("canvas",false));
		txtChart.letData().letCategoriesX().letCategoriesY();
		txtChart.categories("x",this.xCategories(data));
		txtChart.categories("y",this.yCategories());
		txtChart.dataDoubles2(this.toDoubles2(this.demoData()),TxtEchartFunctionFactory.nullify(3));
		txtChart.option(this.jsfOption(jsfGrid,data));
		
		txtChart.init();
	}
	
	private JsonOption jsfOption(JsonGrid jsfGrid, Data data)
	{
		if(Objects.nonNull(jsfGrid.getHeight()))
		{
			Double d = Double.valueOf(jsfGrid.getHeight());
			jsfGrid.setWidth(""+d*data.getValue().getDoubles2().length);
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
	public JsonData xCategories(Data data) {return JsonDataFactory.instance().repeat(data.getValue().getDoubles2().length).build();}
	
	private JsonData toDoubles2(JsonData data)
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		for(int i=0;i<data.getDoubles1().length;i++)
		{
			jf.double2(new double[] {i,0,data.getDoubles1()[i]});
		}
		return jf.build();
	}
	
	// Demo Methods
	public static void demoChart(JsonEchartFactory fEchart) throws IOException
	{
		JsonEchartHeatbarFactory f = JsonEchartHeatbarFactory.instance();
		
		fEchart.letData().letCategoriesX().letCategoriesY();
		fEchart.categories("x",f.demoCategoriesX());
		fEchart.categories("y",f.yCategories());
		fEchart.dataDoubles2(f.toDoubles2(f.demoData()),TxtEchartFunctionFactory.nullify(3));
		fEchart.option(f.demoOption());
	}
	public JsonOption demoOption()
	{
		JsonOption option = new JsonOption();
		option.setGrid(JsonGridFactory.instance().size(12,(12*24)).margin(5,5,5,5).build());
		
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
	public JsonData demoCategoriesX()
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		for (int i=0;i<24;i++)
		{
			jf.string(""+i);
        }
		return jf.build();
	}
	private JsonData demoData()
	{
		Random rnd = new Random();
		JsonDataFactory jf = JsonDataFactory.instance();
		for(int x=0;x<24;x++)
		{
			int value = rnd.nextInt(15)-5;
			if(value<0) {value=0;}
			jf.double1(value);
		}
		return jf.build();
	}
}