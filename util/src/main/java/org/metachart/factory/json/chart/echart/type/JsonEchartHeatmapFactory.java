package org.metachart.factory.json.chart.echart.type;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Random;

import org.exlp.util.JsUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.grid.JsonSplitAreaFactory;
import org.metachart.factory.json.chart.echart.ui.JsonTooltipFactory;
import org.metachart.factory.json.chart.echart.ui.JsonVisualMapFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.metachart.model.json.chart.echart.grid.JsonSplitArea;

public class JsonEchartHeatmapFactory
{
	public static JsonEchartHeatmapFactory instance() {return new JsonEchartHeatmapFactory();}
	private JsonEchartHeatmapFactory()
	{

	}
	
	public JsonOption demoOption()
	{
		JsonOption option = new JsonOption();
		
		JsonSplitArea splitArea = JsonSplitAreaFactory.instance().show(true).build();
		option.setAxisX(JsonAxisFactory.instance().type("category").data("xCategories").splitArea(splitArea).build());
		option.setAxisY(JsonAxisFactory.instance().type("category").data("yCategories").splitArea(splitArea).build());
		option.setVisualMap(JsonVisualMapFactory.instance().show(false).minMax(0,10).build());
		
		option.setTooltip(JsonTooltipFactory.instance().position("top").build());
		
		option.setSeries(new ArrayList<>());
		JsonSeries series = new JsonSeries();	
		series.setType(JsonEchartFactory.Type.heatmap.toString());
		
		series.setData(JsUtil.magicField("data"));
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
		for (DayOfWeek day : DayOfWeek.values())
		{
			jf.string(day.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH));
        }
		return jf.build();
	}
	public JsonData demoData()
	{
		Random rnd = new Random();
		JsonDataFactory jf = JsonDataFactory.instance();
		for(int x=0;x<24;x++)
		{
			for(int y=0;y<7;y++)
			{
				int value = rnd.nextInt(15)-5;
				if(value<0) {value=0;}
				jf.double2(new double[] {x,y,value});
			}
		}
		return jf.build();
	}
}