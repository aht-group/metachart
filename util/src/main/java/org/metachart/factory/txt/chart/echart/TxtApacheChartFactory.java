package org.metachart.factory.txt.chart.echart;

import java.io.IOException;
import java.io.Writer;

import org.metachart.model.json.chart.apache.JsonApache;
import org.metachart.model.json.chart.apache.JsonHtml;

import net.sf.exlp.util.io.JsonUtil;

public class TxtApacheChartFactory
{	
	private final Writer writer;
	private final JsonUtil jom;
	
	private String varChart; public TxtApacheChartFactory varChart(String varChart) {this.varChart=varChart; return this;}
	
	
	public static TxtApacheChartFactory instance(Writer writer, JsonUtil jom) {return new TxtApacheChartFactory(writer,jom);}
	private TxtApacheChartFactory(Writer writer, JsonUtil jom)
	{
		this.writer=writer;
		this.jom=jom;
		varChart = "myChart";
	}
	
	public String declare(String divId, JsonHtml html) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("var dom = document.getElementById('").append(divId).append("');");
		sb.append("\n").append("var "+varChart+" = echarts.init(dom, null, "+jom.toCompactString(html)+");");
		sb.append("\n").append("var option;");
		sb.append("\nlet data = [];");
		writer.write(sb.toString());
		return sb.toString();
	}
	
	public String option(JsonApache echart) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\noption = ").append(jom.toFormattedString(echart));
		writer.write(sb.toString());
		return sb.toString();
	}
	
	public String init() throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\n").append(varChart).append(".setOption(option)");
		sb.append("\nwindow.addEventListener('resize', ").append(varChart).append(".resize);");
		writer.write(sb.toString());
		return sb.toString();
	}
}