package org.metachart.factory.json.chart.echart;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.exlp.util.JsUtil;
import org.metachart.model.json.chart.echart.JsonData;
import org.metachart.model.json.chart.echart.JsonHtml;
import org.metachart.model.json.chart.echart.JsonLink;
import org.metachart.model.json.chart.echart.JsonOption;

import net.sf.exlp.util.io.JsonUtil;

public class JsonEchartFactory
{
	public enum Type{sankey,heatmap}
	
	private final Writer writer;
	private final JsonUtil jom;
	private String varChart; public String getVarChart() {return varChart;}	
	
	public static JsonEchartFactory instance(Writer writer, JsonUtil jom) {return new JsonEchartFactory(writer,jom);}
	private JsonEchartFactory(Writer writer, JsonUtil jom)
	{
		this.writer=writer;
		this.jom=jom;
		varChart = "myChart";
	}
	
	public JsonEchartFactory varChart(String varChart) {this.varChart=varChart; return this;}
	
	public String declare(String divId, JsonHtml html) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("var dom = document.getElementById('").append(divId).append("');");
		sb.append("\n").append("var "+varChart+" = echarts.init(dom, null, "+jom.toCompactString(html)+");");
		sb.append("\n").append("var option;");
		writer.write(sb.toString());
		return sb.toString();
	}
	
	public JsonEchartFactory letData() throws IOException {writer.write("\nlet data = [];"); return this;}
	public JsonEchartFactory letLinks() throws IOException {writer.write("\nlet links = [];");return this;}
	public JsonEchartFactory letCategoriesX() throws IOException {writer.write("\nlet xCategories = [];");return this;}
	public JsonEchartFactory letCategoriesY() throws IOException {writer.write("\nlet yCategories = [];");return this;}
	
	public String option(JsonOption echart) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\noption = ").append(JsUtil.unQuote(jom.toFormattedString(echart)));
		writer.write(sb.toString());
		return sb.toString();
	}
	
	public String data(List<JsonData> list) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\ndata = ").append(jom.toFormattedString(list));
		writer.write(sb.toString());
		return sb.toString();
	}
	public String dataDoubles1(JsonData data) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\ndata = ").append(jom.toFormattedString(data.getDoubles1()));
		sb.append(";");
		writer.write(sb.toString());
		return sb.toString();
	}
	public String dataDoubles2(JsonData data) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\ndata = ").append(jom.toFormattedString(data.getDoubles2()));
		sb.append(";");
		writer.write(sb.toString());
		return sb.toString();
	}
	
	public String categories(String axis, JsonData data) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\n").append(axis.toLowerCase());
		sb.append("Categories = ").append(jom.toFormattedString(data.getStrings()));
		sb.append(";");
		writer.write(sb.toString());
		return sb.toString();
	}
	
	public String links(List<JsonLink> list) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\nlinks = ").append(jom.toFormattedString(list));
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