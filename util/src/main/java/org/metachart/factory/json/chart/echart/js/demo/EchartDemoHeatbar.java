package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonDatasFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory.Type;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.grid.JsonGridFactory;
import org.metachart.factory.json.chart.echart.grid.JsonSplitAreaFactory;
import org.metachart.factory.json.chart.echart.js.family.JsonEchartHeatbarFactory;
import org.metachart.factory.json.chart.echart.ui.JsonOptionFactory;
import org.metachart.factory.json.chart.echart.ui.JsonTooltipFactory;
import org.metachart.factory.json.chart.echart.ui.JsonVisualMapFactory;
import org.metachart.factory.json.function.TxtEchartFunctionFactory;
import org.metachart.factory.txt.chart.TxtDataFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.metachart.model.json.chart.echart.grid.JsonSplitArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchartDemoHeatbar
{
	final static Logger logger = LoggerFactory.getLogger(EchartDemoHeatbar.class);
	
	public static void demo(JsonEchartFactory jfEchart) throws IOException
	{
		JsonEchartHeatbarFactory fHeatbar = JsonEchartHeatbarFactory.instance();
		jfEchart.letData().letCategory("X").letCategory("Y");
		jfEchart.category("X",EchartDemoHeatbar.categoriesX());
		jfEchart.category("Y",JsonEchartHeatbarFactory.yCategories());
		jfEchart.dataDoubles2(fHeatbar.toDoubles2(EchartDemoHeatbar.demoData()),TxtEchartFunctionFactory.nullify(3));
		jfEchart.option(JsonOptionFactory.toMagicDatas(EchartDemoHeatbar.toOption()));
	}
	
	public static JsonOption toOption()
	{
		JsonOption option = new JsonOption();
		option.setGrid(JsonGridFactory.instance().size(12,(12*24)).margin(5,5,5,5).assemble());
		
		JsonSplitArea splitArea = JsonSplitAreaFactory.instance().show(true).build();
		option.setAxisX(JsonAxisFactory.instance().show(false).type("category").data("categoryX").splitArea(splitArea).assemble());
		option.setAxisY(JsonAxisFactory.instance().show(false).type("category").data("categoryY").splitArea(splitArea).assemble());
		option.setVisualMap(JsonVisualMapFactory.instance().show(false).minMax(0,10).build());
		option.setTooltip(JsonTooltipFactory.instance().position("top").assemble());
		
		option.setSeries(new ArrayList<>());
		JsonSeries series = new JsonSeries();
		series.setData(TxtDataFactory.dataId("A"));
		series.setType(JsonEchartFactory.Type.heatmap.toString());
		
		option.getSeries().add(series);
		return option;
	}
	
	public static JsonDatas toDatas()
	{
		JsonDatasFactory jf = JsonDatasFactory.instance();
		jf.add(EchartDemoHeatbar.categoriesX());
		jf.add(JsonEchartHeatbarFactory.yCategories());
		jf.add(EchartDemoHeatbar.demoData());
		return jf.assemble();
	}
	
	private static JsonData categoriesX()
	{
		JsonDataFactory jf = JsonDataFactory.instance().id("X").type(Type.category);
		for (int i=0;i<24;i++)
		{
			jf.string(""+i);
        }
		return jf.assemble();
	}
	
	public static JsonData demoData()
	{
		Random rnd = new Random();
		JsonDataFactory jf = JsonDataFactory.instance().id("A").type(Type.data);
		for(int x=0;x<24;x++)
		{
			int value = rnd.nextInt(15)-5;
			if(value<0) {value=0;}
			jf.double1(value);
		}
		return jf.assemble();
	}
}