package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonSeriesFactory;
import org.metachart.factory.json.chart.echart.grid.JsonMarkAreaFactory;
import org.metachart.factory.json.chart.echart.ui.JsonOptionFactory;
import org.metachart.factory.txt.chart.TxtDataFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchartDemoTime
{
	final static Logger logger = LoggerFactory.getLogger(EchartDemoTime.class);
	
	public static void demo(JsonEchartFactory jfEchart) throws IOException
	{
		jfEchart.letData("A").letData("B").letData("AreaSeries");
		jfEchart.dataTime(EchartDemoTime.toData("A"));
		jfEchart.dataTime(EchartDemoTime.toData("B"));
		jfEchart.option(JsonOptionFactory.toMagicDatas(EchartDemoTime.toOption()));
	}
	
	public static JsonOption toOption()
	{
		JsonOption option =  JsonOptionFactory.instance().time2().grid("10", "25", "20", "10").assemble();
		
		JsonSeries seriesA = new JsonSeries();
		seriesA.setType(JsonEchartFactory.Type.line.toString());
		seriesA.setData(TxtDataFactory.dataId("A"));
		logger.info(seriesA.getData());
		
		JsonSeries seriesB = new JsonSeries();
		seriesB.setType(JsonEchartFactory.Type.line.toString());
		seriesB.setData(TxtDataFactory.dataId("B"));
		seriesB.setSmooth(true);
		
		JsonSeriesFactory jfSeriesArea = JsonSeriesFactory.instance().type(JsonEchartFactory.Type.line);
		jfSeriesArea.data("AreaSeries");
		JsonMarkAreaFactory jfArea = JsonMarkAreaFactory.instance().data(EchartDemoTime.toDataArea());
		jfSeriesArea.markArea(jfArea.assemble());
		
		option.setSeries(new ArrayList<>());
		option.getSeries().add(seriesA);
		option.getSeries().add(seriesB);
		option.getSeries().add(jfSeriesArea.assemble());
	
		return option;
	}
	
	public static JsonDatas toDatas()
	{
		JsonDatas datas = new JsonDatas();
		datas.setList(new ArrayList<>());
		datas.getList().add(EchartDemoTime.toData("A"));
		datas.getList().add(EchartDemoTime.toData("B"));
		datas.getList().add(EchartDemoTime.toDataArea());
		
		return datas;
	}
	
	public static JsonData toDataArea()
	{
		JsonDataFactory jfAxis = JsonDataFactory.instance().id("AreaSeries");
		jfAxis.axisRange(LocalDateTime.now().plusHours(10), LocalDateTime.now().plusHours(20));
		return jfAxis.assemble();
	}
	
	private static JsonData toData(String seriesId)
	{
		Random rnd = new Random();
		LocalDateTime ldt = LocalDateTime.now();
		
		JsonDataFactory jf = JsonDataFactory.instance().id(seriesId);
		EchartDemoTime.add(jf,ldt, 05, 2, rnd);
		EchartDemoTime.add(jf,ldt, 10, 3, rnd);
		EchartDemoTime.add(jf,ldt, 15, 1, rnd);
		EchartDemoTime.add(jf,ldt, 20, 2, rnd);
		EchartDemoTime.add(jf,ldt, 21, 3, rnd);
		EchartDemoTime.add(jf,ldt, 22, 5, rnd);
		EchartDemoTime.add(jf,ldt, 23, 4, rnd);

		return jf.assemble();
	}
		
	private static void add(JsonDataFactory jf, LocalDateTime ldt, int hours, double value, Random rnd)
	{
		jf.time(ldt.plusHours(hours),value+ 2*(rnd.nextDouble()-0.5));
	}
}