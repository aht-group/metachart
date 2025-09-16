package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import org.exlp.util.io.JsUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.txt.chart.TxtDataFactory;
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
		jfEchart.letData("A").letData("B");
		jfEchart.dataTime(EchartDemoTime.toData("A"));
		jfEchart.dataTime(EchartDemoTime.toData("B"));
		jfEchart.option(this.toOption());
	}
	
	private JsonOption toOption()
	{
		JsonOption option = new JsonOption();
		option.setAxisX(JsonAxisFactory.instance().type("time").build());
		option.setAxisY(JsonAxisFactory.instance().type("value").build());
		
		JsonSeries seriesA = new JsonSeries();
		seriesA.setType(JsonEchartFactory.Type.line.toString());
		seriesA.setData(JsUtil.magicField(TxtDataFactory.id(id,"A")));
		
		JsonSeries seriesB = new JsonSeries();
		seriesB.setType(JsonEchartFactory.Type.line.toString());
		seriesB.setData(JsUtil.magicField(TxtDataFactory.id(id,"B")));
		seriesB.setSmooth(true);
		
		option.setSeries(new ArrayList<>());
		option.getSeries().add(seriesA);
		option.getSeries().add(seriesB);
		
		option.setUseUtc(true);
		
		return option;
	}
	
	public static JsonData toData(String seriesId)
	{
		Random rnd = new Random();
		
		LocalDateTime ldt = LocalDateTime.now();
		
		JsonDataFactory jf = JsonDataFactory.instance().id(seriesId);
		jf.time(ldt.plusHours(5),2+rnd.nextDouble());
		jf.time(ldt.plusHours(10),3+rnd.nextDouble());
		jf.time(ldt.plusHours(15),1+rnd.nextDouble());
		jf.time(ldt.plusHours(20),2+rnd.nextDouble());
		jf.time(ldt.plusHours(21),3+rnd.nextDouble());
		jf.time(ldt.plusHours(22),5+rnd.nextDouble());
		jf.time(ldt.plusHours(23),4+rnd.nextDouble());
		
		JsonData data = jf.assemble();
		return data;
	}
}