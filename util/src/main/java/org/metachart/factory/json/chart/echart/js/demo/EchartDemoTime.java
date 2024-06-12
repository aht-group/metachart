package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.exlp.util.io.JsUtil;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchartDemoTime
{
	final static Logger logger = LoggerFactory.getLogger(EchartDemoTime.class);

	private final JsonEchartFactory jfEchart;
	
	private String id; public EchartDemoTime id(String id) {this.id=id; return this;}
	
	public static EchartDemoTime instance(JsonEchartFactory fEchart) {return new EchartDemoTime(fEchart);}
	private EchartDemoTime(JsonEchartFactory jfEchart)
	{
		this.jfEchart=jfEchart;
		id="";
	}
	
	public void demo() throws IOException
	{
		jfEchart.letData();
//		jfEchart.categories("X",this.toCategoriesX());
		jfEchart.dataTime(this.toData());
		jfEchart.option(this.toOption());
	}
	
	public JsonOption toOption()
	{
		JsonOption option = new JsonOption();
		option.setAxisX(JsonAxisFactory.instance().type("time").build());
		option.setAxisY(JsonAxisFactory.instance().type("value").build());
		
		JsonSeries series = new JsonSeries();
		series.setType(JsonEchartFactory.Type.line.toString());
		series.setData(JsUtil.magicField("data"+id));
		
		option.setSeries(new ArrayList<>());
		option.getSeries().add(series);
		
		return option;
	}
	
	private JsonData toData()
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		JsonDataFactory jf = JsonDataFactory.instance();
		jf.time(ldt.plusHours(5),2);
		jf.time(ldt.plusHours(5),3);
		jf.time(ldt.plusHours(5),4);
		jf.time(ldt.plusHours(5),2);
		jf.time(ldt.plusHours(5),3);
		jf.time(ldt.plusHours(5),3);
		
		JsonData data = jf.build();
		JsonUtil.info(data);
		return data;
	}
}