package org.metachart.factory.json.chart;

import java.io.IOException;
import java.io.Writer;

import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.type.JsonEchartHeatmapFactory;
import org.metachart.factory.json.chart.echart.type.JsonEchartSankeyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.io.JsonUtil;

public class EchartProvider
{
	final static Logger logger = LoggerFactory.getLogger(EchartProvider.class);
	
	public static void demo(Writer w, String type, String divId) throws IOException
	{
		JsonEchartFactory.Type t = JsonEchartFactory.Type.valueOf(type);
		EchartProvider.demo(w,t,divId);
	}
	public static void demo(Writer w, JsonEchartFactory.Type type, String divId) throws IOException
	{				
		JsonUtil jom = JsonUtil.instance();
		JsonEchartFactory txtChart = JsonEchartFactory.instance(w,jom);
	
		txtChart.declare(divId,JsonHtmlFactory.build("canvas",false));
		
		switch(type)
		{
			case sankey: sankey(txtChart); break;
			case heatmap: heatmap(txtChart); break;
		}
		
		txtChart.init();
	}
	
	private static void sankey(JsonEchartFactory echartFactory) throws IOException
	{
		JsonEchartSankeyFactory f = JsonEchartSankeyFactory.instance();
		
		echartFactory.letData().letLinks();
		echartFactory.data(f.demoData());
		echartFactory.links(f.demoLinks());
		echartFactory.option(f.demoOption());
	}
	
	private static void heatmap(JsonEchartFactory echartFactory) throws IOException
	{
		JsonEchartHeatmapFactory f = JsonEchartHeatmapFactory.instance();
		
		echartFactory.letData().letCategoriesX().letCategoriesY();
		echartFactory.categories("x",f.demoCategoriesX());
		echartFactory.categories("y",f.demoCategoriesY());
		echartFactory.dataDoubles2(f.demoData());
		echartFactory.option(f.demoOption());
	}
}