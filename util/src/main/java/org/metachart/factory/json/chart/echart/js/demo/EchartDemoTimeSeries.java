package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.exlp.util.io.JsUtil;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonTsDataFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchartDemoTimeSeries
{
	final static Logger logger = LoggerFactory.getLogger(EchartDemoTimeSeries.class);

	private final JsonEchartFactory jfEchart;

	private String id; public EchartDemoTimeSeries id(String id) {this.id=id; return this;}

	public static EchartDemoTimeSeries instance(JsonEchartFactory fEchart) {return new EchartDemoTimeSeries(fEchart);}
	private EchartDemoTimeSeries(JsonEchartFactory jfEchart)
	{
		this.jfEchart=jfEchart;
		id="";
	}

	public void demo() throws IOException
	{
		jfEchart.letData();
//		jfEchart.categories("X",this.toCategoriesX());
//		jfEchart.dataTime(this.toData());
		jfEchart.option(this.toOption());
	}

	public JsonOption toOption()
	{
		JsonOption option = new JsonOption();
//		option.setUseUTC(true);
		option.setAxisX(JsonAxisFactory.instance().type("time").build());
		option.setAxisY(JsonAxisFactory.instance().type("value").build());

		JsonSeries series = new JsonSeries();
		series.setName("Timeseries Example");
		series.setType(JsonEchartFactory.Type.line.toString());
		series.setData(JsUtil.magicField("data"+id));

		option.setSeries(new ArrayList<>());
		option.getSeries().add(series);

		return option;
	}

//	private JsonTsData toData()
//	{
//		LocalDateTime ldt = LocalDateTime.now();
//
//		JsonTsDataFactory jf = JsonTsDataFactory.instance();
//		jf.addTsData(ldt.plusDays(5),2);
//		jf.addTsData(ldt.plusDays(10),3);
//		jf.addTsData(ldt.plusDays(15),4);
//		jf.addTsData(ldt.plusDays(20),2);
//		jf.addTsData(ldt.plusDays(25),3);
//		jf.addTsData(ldt.plusDays(35),3);
//
//		JsonTsData data = jf.build();
//		JsonUtil.info(data);
//		return data;
//	}
}