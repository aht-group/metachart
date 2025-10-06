package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.util.ArrayList;

import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonDatasFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.ui.JsonOptionFactory;
import org.metachart.factory.txt.chart.TxtDataFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchartDemoCategory
{
	final static Logger logger = LoggerFactory.getLogger(EchartDemoCategory.class);
	
	public static void demo(JsonEchartFactory jfEchart) throws IOException
	{
		jfEchart.letCategory("X").letData("A");
		jfEchart.category("X",EchartDemoCategory.toCategory("X"));
		jfEchart.dataDoubles1(EchartDemoCategory.toData("A"));
		jfEchart.option(JsonOptionFactory.toMagicDatas(EchartDemoCategory.toOption()));
	}
	
	public static JsonOption toOption() 
	{
		JsonOption option = new JsonOption();
		option.setAxisX(JsonAxisFactory.instance().type("category").data(TxtDataFactory.categoryId("X")).assemble());
		option.setAxisY(JsonAxisFactory.instance().type("value").assemble());
		
		JsonSeries series = new JsonSeries();
		series.setType(JsonEchartFactory.Type.line.toString());
		series.setData(TxtDataFactory.dataId("A"));
		
		logger.info(series.getData());
		
		option.setSeries(new ArrayList<>());
		option.getSeries().add(series);
		
		return option;
	}
	
	public static JsonDatas toDatas()
	{
		JsonDatasFactory jf = JsonDatasFactory.instance();
		jf.add(EchartDemoCategory.toCategory("X"));
		jf.add(EchartDemoCategory.toData("A"));
		
		return jf.assemble();
	}
	
	private static JsonData toCategory(String seriesId)
	{
		JsonDataFactory jf = JsonDataFactory.instance().type(JsonDataFactory.Type.category).id(seriesId);
		jf.string("Mon");
		jf.string("Tue");
		jf.string("Wed");
		jf.string("Thu");
		jf.string("Fri");
		jf.string("Sat");
		jf.string("Sun");
		return jf.assemble();
	}

	public static JsonData toData(String seriesId)
	{
		JsonDataFactory jf = JsonDataFactory.instance().type(JsonDataFactory.Type.data).id(seriesId);
		jf.double1(1);
		jf.double1(2);
		jf.double1(3);
		jf.double1(4);
		jf.double1(2);
		jf.double1(3);
		jf.double1(3);
		return jf.assemble();
	}
}