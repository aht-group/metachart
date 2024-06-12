package org.metachart.factory.json.chart;

import java.io.IOException;
import java.io.Writer;

import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoGraph;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoHeatbar;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoHeatmap;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoLine;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoSankey;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoTime;
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
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(w,JsonUtil.instance()).declare(divId,JsonHtmlFactory.build("canvas",false));
		switch(type)
		{
			case line: EchartDemoLine.instance(jfEchart).demo(); break;
			case time: EchartDemoTime.instance(jfEchart).demo(); break;
			case sankey: EchartDemoSankey.instance(jfEchart).demo(); break;
			case heatmap: EchartDemoHeatmap.instance(jfEchart).demo(); break;
			case heatbar: EchartDemoHeatbar.instance(jfEchart).demo(); break;
			case graph: EchartDemoGraph.instance(jfEchart).demo(); break;
			default: logger.warn("NYI: "+type.toString());
		}
		jfEchart.init();
	}
}