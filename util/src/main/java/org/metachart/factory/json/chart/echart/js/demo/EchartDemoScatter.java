package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.exlp.util.io.JsUtil;
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

	private final JsonEchartFactory jfEchart;
	
	private String id; public EchartDemoScatter id(String id) {this.id=id; return this;}
	
	public static EchartDemoScatter instance(JsonEchartFactory fEchart) {return new EchartDemoScatter(fEchart);}
	private EchartDemoScatter(JsonEchartFactory jfEchart)
	{
		this.jfEchart=jfEchart;
		id="";
	}
	
	public void demo() throws IOException
	{
		jfEchart.letData("A");
		jfEchart.dataDoubles2(EchartDemoScatter.toData("A"));
		jfEchart.option(this.toOption(true));
	}
	
	public JsonOption toOption(boolean withMagic)
	{
		JsonOption option = JsonOptionFactory.instance().scatter().assemble();
		
		JsonSeries seriesA = new JsonSeries();
		seriesA.setType(JsonEchartFactory.Type.scatter.toString());
		seriesA.setData(withMagic ? JsUtil.magicField(TxtDataFactory.dataId(id,"A")) : TxtDataFactory.id(id,"A"));

		option.setSeries(new ArrayList<>());
		option.getSeries().add(seriesA);
	
		return option;
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