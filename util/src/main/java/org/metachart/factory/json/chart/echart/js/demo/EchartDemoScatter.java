package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonDatasFactory;
import org.metachart.factory.json.chart.echart.js.family.JsonEchartScatterFactory;
import org.metachart.factory.json.chart.echart.ui.JsonOptionFactory;
import org.metachart.factory.json.chart.echart.ui.JsonTooltipFactory;
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
		jfEchart.letData("A").letData("B");
		jfEchart.dataDoubles2(EchartDemoScatter.toData("A"));
		jfEchart.dataDate1(EchartDemoScatter.toDataDays("B"));
		jfEchart.function(JsonEchartScatterFactory.functionTooltipDate("dataB"));
		jfEchart.option(JsonOptionFactory.toMagicDatas(EchartDemoScatter.toOption()));
	}
	
	public static JsonOption toOption()
	{
		JsonOptionFactory jfOption = JsonOptionFactory.instance().scatter();

		JsonTooltipFactory jfTt = JsonTooltipFactory.instance().triggerItem().formatter("scatterTooltipFormatter");
		
		jfOption.tooltip(jfTt.build());
		
		JsonSeries seriesA = new JsonSeries();
		seriesA.setType(JsonEchartFactory.Type.scatter.toString());
		seriesA.setData(TxtDataFactory.dataId("A"));

		jfOption.series(seriesA);
		
	
		return jfOption.assemble();
	}
	
	public static JsonDatas toDatas()
	{
		JsonDatasFactory jf = JsonDatasFactory.instance();
		jf.add(EchartDemoScatter.toData("A"));
		jf.add(EchartDemoScatter.toDataDays("B"));
		return jf.assemble();
	}
	
	public static JsonData toData(String seriesId)
	{
		Random rnd = new Random();
		
		JsonDataFactory jf = JsonDataFactory.instance().id(seriesId).type(JsonDataFactory.Type.data);
		for(int i=0;i<15;i++)
		{
			jf.double2(new double[]{rnd.nextDouble()*i, rnd.nextDouble()*i});
		}

		return jf.assemble();
	}
	
	public static JsonData toDataDays(String seriesId)
	{
		JsonDataFactory jf = JsonDataFactory.instance().id(seriesId).type(JsonDataFactory.Type.dates);
		for(int i=0;i<15;i++)
		{
			jf.date(LocalDate.now().minusDays(i));
		}

		return jf.assemble();
	}
}