package org.metachart.factory.json.chart;

import java.io.IOException;
import java.io.Writer;

import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.type.JsonEchartHeatbarFactory;
import org.metachart.factory.json.chart.echart.type.JsonEchartHeatmapFactory;
import org.metachart.factory.json.chart.echart.type.JsonEchartSankeyFactory;
import org.metachart.factory.json.function.TxtEchartFunctionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.io.JsonUtil;

public class EchartProvider
{
	final static Logger logger = LoggerFactory.getLogger(EchartProvider.class);
	
	private Writer w;
	
	public static EchartProvider instance(Writer w) {return new EchartProvider(w);}
	public EchartProvider(Writer w)
	{
		this.w=w;
	}
	
	public void demo(String type, String divId) throws IOException {demo(JsonEchartFactory.Type.valueOf(type),divId);}
	public void demo(JsonEchartFactory.Type type, String divId) throws IOException
	{	
		JsonEchartFactory txtChart = JsonEchartFactory.instance(w,JsonUtil.instance()).declare(divId,JsonHtmlFactory.build("canvas",false));
		switch(type)
		{
			case sankey: sankey(txtChart); break;
			case heatmap: heatmap(txtChart); break;
			case heatbar: JsonEchartHeatbarFactory.demoChart(txtChart); break;
		}
		txtChart.init();
	}
	
	private static void sankey(JsonEchartFactory fEchart) throws IOException
	{
		JsonEchartSankeyFactory f = JsonEchartSankeyFactory.instance();
		
		fEchart.letData().letLinks();
		fEchart.data(f.demoData());
		fEchart.links(f.demoLinks());
		fEchart.option(f.demoOption());
	}
	
	private static void heatmap(JsonEchartFactory fEchart) throws IOException
	{
		JsonEchartHeatmapFactory f = JsonEchartHeatmapFactory.instance();
		
		fEchart.letData().letCategoriesX().letCategoriesY();
		fEchart.categories("x",f.demoCategoriesX());
		fEchart.categories("y",f.demoCategoriesY());
		fEchart.dataDoubles2(f.demoData(),TxtEchartFunctionFactory.nullify(3));
		fEchart.option(f.demoOption());
	}
}