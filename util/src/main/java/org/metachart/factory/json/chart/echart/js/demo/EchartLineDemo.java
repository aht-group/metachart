package org.metachart.factory.json.chart.echart.js.demo;

import java.util.ArrayList;

import org.exlp.util.io.JsUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.interfaces.data.EchartLineDataProvider;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchartLineDemo implements EchartLineDataProvider
{
	final static Logger logger = LoggerFactory.getLogger(EchartLineDemo.class);
	
	private String id; public EchartLineDemo id(String id) {this.id=id; return this;}
	
	public static EchartLineDemo instance() {return new EchartLineDemo();}
	private EchartLineDemo()
	{

		id="";
	}
	
//	public void demo(JsonEchartFactory fEchart) throws IOException
//	{
//		fEchart.letCategories("X").letData().letEdges();
//		fEchart.categories("Node",this.getGraphCategories().getData());
//		fEchart.data(this.getGraphNodes().getData());
//		fEchart.edges(this.getGraphEdges().getEdges());
//		fEchart.option(this.demoOption());
//	}
	public JsonOption demoOption()
	{
		JsonOption option = new JsonOption();
		option.setAxisX(JsonAxisFactory.instance().type("category").data("categoriesX").build());
		option.setAxisY(JsonAxisFactory.instance().type("value").build());
		
		option.setSeries(new ArrayList<>());
	
		JsonSeries series = new JsonSeries();
		series.setType(JsonEchartFactory.Type.line.toString());
		series.setData(JsUtil.magicField("data"+id));
		
		option.getSeries().add(series);
		return option;
	}
	
	@Override public JsonData getLineCategories()
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
	
	@Override public JsonData getLineData()
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