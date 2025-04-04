package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.util.ArrayList;

import org.exlp.util.io.JsUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonEdgeFactory;
import org.metachart.factory.json.chart.echart.ui.JsonTooltipFactory;
import org.metachart.interfaces.data.EchartGaugeDataProvider;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchartDemoGauge implements EchartGaugeDataProvider
{
	final static Logger logger = LoggerFactory.getLogger(EchartDemoGauge.class);
	
	private final JsonEchartFactory jfEchart;
	
	private String id; public EchartDemoGauge id(String id) {this.id=id; return this;}
	
	public static EchartDemoGauge instance(JsonEchartFactory fEchart) {return new EchartDemoGauge(fEchart);}
	private EchartDemoGauge(JsonEchartFactory jfEchart)
	{
		this.jfEchart=jfEchart;
		id="";
	}
	
	public void demo() throws IOException
	{
		jfEchart.dataDoubles1(this.getGaugeData());
		jfEchart.option(this.demoOption());
	}
	public JsonOption demoOption()
	{
		JsonOption option = new JsonOption();
			
		JsonSeries series = new JsonSeries();
		series.setType(JsonEchartFactory.Type.gauge.toString());
		series.setMin(0d);
		series.setMax(900d);
		series.setData(JsUtil.magicField("data"+id));
		
		
		option.setSeries(new ArrayList<>());	
		option.getSeries().add(series);
		return option;
	}
	
	@Override public JsonData getGaugeData()
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		jf.double1(400);

		return jf.build();
	}
	
}