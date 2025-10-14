package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.exlp.util.io.JsUtil;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.grid.JsonGridFactory;
import org.metachart.factory.json.chart.echart.grid.JsonSplitAreaFactory;
import org.metachart.factory.json.chart.echart.ui.JsonOptionFactory;
import org.metachart.factory.json.chart.echart.ui.JsonTooltipFactory;
import org.metachart.factory.json.chart.echart.ui.JsonVisualMapFactory;
import org.metachart.factory.json.function.TxtEchartFunctionFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.metachart.model.json.chart.echart.grid.JsonSplitArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchartDemoHeatbar
{
	final static Logger logger = LoggerFactory.getLogger(EchartDemoHeatbar.class);
	
	private final JsonEchartFactory fEchart;
	
	private String id; public EchartDemoHeatbar id(String id) {this.id=id; return this;}
	
	public static EchartDemoHeatbar instance(JsonEchartFactory fEchart) {return new EchartDemoHeatbar(fEchart);}
	private EchartDemoHeatbar(JsonEchartFactory fEchart)
	{
		this.fEchart=fEchart;
		id="";
	}
	
	public void demo() throws IOException
	{
		org.metachart.factory.json.chart.echart.js.heat.JsonEchartHeatbarFactory fHeatbar = org.metachart.factory.json.chart.echart.js.heat.JsonEchartHeatbarFactory.instance();
		
		fEchart.letData().letCategory("X").letCategory("Y");
		fEchart.category("X",this.categoriesX());
		fEchart.category("Y",fHeatbar.yCategories());
		fEchart.dataDoubles2(fHeatbar.toDoubles2(this.demoData()),TxtEchartFunctionFactory.nullify(3));
		fEchart.option(JsonOptionFactory.toMagicDatas(this.demoOption()));
	}
	
	public JsonOption demoOption()
	{
		JsonOption option = new JsonOption();
		option.setGrid(JsonGridFactory.instance().size(12,(12*24)).margin(5,5,5,5).assemble());
		
		JsonSplitArea splitArea = JsonSplitAreaFactory.instance().show(true).build();
		option.setAxisX(JsonAxisFactory.instance().show(false).type("category").data("categoryX").splitArea(splitArea).assemble());
		option.setAxisY(JsonAxisFactory.instance().show(false).type("category").data("categoryY").splitArea(splitArea).assemble());
		JsonUtil.info(option);
		option.setVisualMap(JsonVisualMapFactory.instance().show(false).minMax(0,10).build());
		option.setTooltip(JsonTooltipFactory.instance().position("top").assemble());
		
		option.setSeries(new ArrayList<>());
		JsonSeries series = new JsonSeries();
		series.setData(JsUtil.magicField("data"+id));
		series.setType(JsonEchartFactory.Type.heatmap.toString());
		
		option.getSeries().add(series);
		return option;
	}
	
	private JsonData categoriesX()
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		for (int i=0;i<24;i++)
		{
			jf.string(""+i);
        }
		return jf.assemble();
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
		return jf.assemble();
	}
}