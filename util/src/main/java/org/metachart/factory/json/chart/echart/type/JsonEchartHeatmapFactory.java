package org.metachart.factory.json.chart.echart.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.exlp.util.JsUtil;
import org.metachart.factory.json.chart.echart.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonLabelFactory;
import org.metachart.factory.json.chart.echart.JsonLineStyleFactory;
import org.metachart.factory.json.chart.echart.JsonTooltipFactory;
import org.metachart.factory.json.chart.echart.JsonVisualMapFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonLinkFactory;
import org.metachart.model.json.chart.echart.JsonData;
import org.metachart.model.json.chart.echart.JsonLink;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.JsonSeries;

public class JsonEchartHeatmapFactory
{
	public static JsonEchartHeatmapFactory instance() {return new JsonEchartHeatmapFactory();}
	private JsonEchartHeatmapFactory()
	{
		
	}
	
	public JsonOption demoOption()
	{
		
		JsonOption option = new JsonOption();
		
		option.setAxisX(JsonAxisFactory.instance().type("category").data("xCategories").build());
		option.setAxisY(JsonAxisFactory.instance().type("category").data("yCategories").build());
		
		option.setVisualMap(JsonVisualMapFactory.instance().minMax(0,10).build());
		
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
		jf.string("A").string("B").string("C");
		return jf.build();
	}
	public JsonData demoCategoriesY()
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		jf.string("1").string("2").string("3");
		return jf.build();
	}
	public JsonData demoData()
	{
		Random rnd = new Random();
		JsonDataFactory jf = JsonDataFactory.instance();
		for(int x=0;x<3;x++)
		{
			for(int y=0;y<3;y++)
			{
				jf.double2(new double[] {x,y,rnd.nextInt(10)});
			}
		}
		return jf.build();
	}
}