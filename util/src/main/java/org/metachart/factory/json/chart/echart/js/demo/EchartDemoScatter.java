package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.ui.JsonOptionFactory;
import org.metachart.factory.txt.chart.TxtDataFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchartDemoScatter
{
	final static Logger logger = LoggerFactory.getLogger(EchartDemoScatter.class);
	
	public static void demo(JsonEchartFactory jfEchart) throws IOException
	{
		jfEchart.letData("A");
		jfEchart.dataDoubles2(EchartDemoScatter.toData("A"));
		jfEchart.option(JsonOptionFactory.toMagicDatas(EchartDemoScatter.toOption()));
	}
	
	public static JsonOption toOption()
	{
		JsonOptionFactory jfOption = JsonOptionFactory.instance().scatter();

		JsonSeries seriesA = new JsonSeries();
		seriesA.setType(JsonEchartFactory.Type.scatter.toString());
		seriesA.setData(TxtDataFactory.dataId("A"));

		jfOption.series(seriesA);
	
		return jfOption.assemble();
	}
	
	public static JsonDatas toDatas()
	{
		JsonDatas datas = new JsonDatas();
		datas.setList(new ArrayList<>());
		datas.getList().add(EchartDemoScatter.toData("A"));
		return datas;
	}
	
	public static JsonData toData(String seriesId)
	{
		Random rnd = new Random();
		
		JsonDataFactory jf = JsonDataFactory.instance().id(seriesId);
		for(int i=0;i<15;i++)
		{
			jf.double2(new double[]{rnd.nextDouble()*i, rnd.nextDouble()*i});
		}

		return jf.assemble();
	}
}