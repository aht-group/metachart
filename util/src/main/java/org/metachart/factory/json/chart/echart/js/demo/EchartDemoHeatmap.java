package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Random;

import org.exlp.util.io.JsUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.grid.JsonSplitAreaFactory;
import org.metachart.factory.json.chart.echart.ui.JsonTooltipFactory;
import org.metachart.factory.json.chart.echart.ui.JsonVisualMapFactory;
import org.metachart.factory.json.function.TxtEchartFunctionFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.metachart.model.json.chart.echart.grid.JsonSplitArea;

public class EchartDemoHeatmap
{
	private final JsonEchartFactory fEchart;
	
	public static EchartDemoHeatmap instance(JsonEchartFactory fEchart) {return new EchartDemoHeatmap(fEchart);}
	private EchartDemoHeatmap(JsonEchartFactory fEchart)
	{
		this.fEchart=fEchart;
	}
	
	public void demo() throws IOException
	{		
		fEchart.letData().letCategoriesX().letCategoriesY();
		fEchart.categories("x",this.demoCategoriesX());
		fEchart.categories("y",this.demoCategoriesY());
		fEchart.dataDoubles2(this.demoData(),TxtEchartFunctionFactory.nullify(3));
		fEchart.option(this.demoOption());
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
		return jf.assemble();
	}
	public JsonData demoCategoriesY()
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		for (DayOfWeek day : DayOfWeek.values())
		{
			jf.string(day.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH));
        }
		return jf.assemble();
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
		return jf.assemble();
	}
}