package org.metachart.factory.json.chart;

import java.io.IOException;
import java.io.Writer;

import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoGraph;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoHeatbar;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoHeatmap;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoSankey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
			case sankey: EchartDemoSankey.instance(txtChart).demo(); break;
			case heatmap: EchartDemoHeatmap.instance(txtChart).demo(); break;
			case heatbar: EchartDemoHeatbar.instance(txtChart).demo(); break;
			case graph: EchartDemoGraph.instance().demo(txtChart); break;
		}
		txtChart.init();
	}
}