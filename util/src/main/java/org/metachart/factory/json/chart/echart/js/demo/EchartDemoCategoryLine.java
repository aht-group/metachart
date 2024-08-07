package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.util.ArrayList;

import org.exlp.util.io.JsUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchartDemoCategoryLine
{
	final static Logger logger = LoggerFactory.getLogger(EchartDemoCategoryLine.class);

	private final JsonEchartFactory jfEchart;
	
	private String id; public EchartDemoCategoryLine id(String id) {this.id=id; return this;}
	
	public static EchartDemoCategoryLine instance(JsonEchartFactory fEchart) {return new EchartDemoCategoryLine(fEchart);}
	private EchartDemoCategoryLine(JsonEchartFactory jfEchart)
	{
		this.jfEchart=jfEchart;
		id="";
	}
	
	public void demo() throws IOException
	{
		jfEchart.letCategories("X").letData();
		jfEchart.categories("X",EchartDemoCategoryLine.toCategoriesX());
		jfEchart.dataDoubles1(EchartDemoCategoryLine.toData());
		jfEchart.option(this.toOption());
	}
	
	public JsonOption toOption()
	{
		JsonOption option = new JsonOption();
		option.setAxisX(JsonAxisFactory.instance().type("category").data("categoriesX").build());
		option.setAxisY(JsonAxisFactory.instance().type("value").build());
		
		JsonSeries series = new JsonSeries();
		series.setType(JsonEchartFactory.Type.line.toString());
		series.setData(JsUtil.magicField("data"+id));
		
		option.setSeries(new ArrayList<>());
		option.getSeries().add(series);
		
		return option;
	}
	
	public static JsonData toCategoriesX()
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		jf.string("Mon");
		jf.string("Tue");
		jf.string("Wed");
		jf.string("Thu");
		jf.string("Fri");
		jf.string("Sat");
		jf.string("Sun");
		return jf.build();
	}
	
	public static JsonData toData()
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		jf.double1(1);
		jf.double1(2);
		jf.double1(3);
		jf.double1(4);
		jf.double1(2);
		jf.double1(3);
		jf.double1(3);
		return jf.build();
	}
}