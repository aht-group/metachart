package org.metachart.factory.json.chart;

import java.io.IOException;
import java.io.Writer;

import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
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
		}
		
		txtChart.init();
	}
	
	private static void sankey(JsonEchartFactory echartFactory) throws IOException
	{
		JsonEchartSankeyFactory sankeyFactory = JsonEchartSankeyFactory.instance();
		
		echartFactory.letData().letLinks();
		echartFactory.data(sankeyFactory.demoData());
		echartFactory.links(sankeyFactory.demoLinks());
		echartFactory.option(sankeyFactory.demoOption());
	}
}