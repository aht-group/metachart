package org.metachart.factory.txt.chart.echarts;

import org.metachart.model.json.chart.apache.JsonApache;
import org.metachart.model.json.chart.apache.JsonHtml;

import net.sf.exlp.util.io.JsonUtil;

public class TxtApacheChartFactory
{	
	private final JsonUtil jom;
	
	private String varChart; public TxtApacheChartFactory varChart(String varChart) {this.varChart=varChart; return this;}
	
	public static TxtApacheChartFactory instance(JsonUtil jom) {return new TxtApacheChartFactory(jom);}
	private TxtApacheChartFactory(JsonUtil jom)
	{
		this.jom=jom;
		varChart = "myChart";
	}
	
	public String declare(String divId, JsonHtml html)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("var dom = document.getElementById('").append(divId).append("');");
		sb.append("\n").append("var "+varChart+" = echarts.init(dom, null, "+jom.toCompactString(html)+");");
		sb.append("\n").append("var option;");
		sb.append("\nlet data = [];");
		return sb.toString();
	}
	
	public String option(JsonApache echart)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\noption = ").append(jom.toFormattedString(echart));
		return sb.toString();
	}
	
	public String init()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\n").append(varChart).append(".setOption(option)");
		sb.append("\nwindow.addEventListener('resize', ").append(varChart).append(".resize);");
		return sb.toString();
	}
}