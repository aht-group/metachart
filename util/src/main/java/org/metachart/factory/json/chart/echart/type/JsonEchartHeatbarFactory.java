package org.metachart.factory.json.chart.echart.type;

import java.util.ArrayList;
import java.util.Random;

import org.exlp.util.JsUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonTooltipFactory;
import org.metachart.factory.json.chart.echart.JsonVisualMapFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.grid.JsonGridFactory;
import org.metachart.factory.json.chart.echart.grid.JsonSplitAreaFactory;
import org.metachart.model.json.chart.echart.JsonData;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.JsonSeries;
import org.metachart.model.json.chart.echart.grid.JsonSplitArea;

public class JsonEchartHeatbarFactory
{
	public static JsonEchartHeatbarFactory instance() {return new JsonEchartHeatbarFactory();}
	private JsonEchartHeatbarFactory()
	{
		
	}
	
	public JsonOption demoOption()
	{
		JsonOption option = new JsonOption();
		option.setGrid(JsonGridFactory.instance().maring(5,5,5,5).build());
		
		JsonSplitArea splitArea = JsonSplitAreaFactory.instance().show(true).build();
		option.setAxisX(JsonAxisFactory.instance().show(false).type("category").data("xCategories").splitArea(splitArea).build());
		option.setAxisY(JsonAxisFactory.instance().show(false).type("category").data("yCategories").splitArea(splitArea).build());
		option.setVisualMap(JsonVisualMapFactory.instance().show(false).minMax(0,10).build());
		option.setTooltip(JsonTooltipFactory.instance().position("top").build());
		
		option.setSeries(new ArrayList<>());
		JsonSeries series = new JsonSeries();
		series.setData(JsUtil.magicField("data"));
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
	public JsonData demoCategoriesY()
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		jf.string("A");
		return jf.build();
	}
	public JsonData demoData()
	{
		Random rnd = new Random();
		JsonDataFactory jf = JsonDataFactory.instance();
		for(int x=0;x<24;x++)
		{
			int value = rnd.nextInt(15)-5;
			if(value<0) {value=0;}
			jf.double2(new double[] {x,0,value});
		}
		return jf.build();
	}
}