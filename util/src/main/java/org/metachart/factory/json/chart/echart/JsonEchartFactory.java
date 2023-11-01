package org.metachart.factory.json.chart.echart;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Objects;

import org.exlp.util.JsUtil;
import org.metachart.model.json.chart.echart.JsonData;
import org.metachart.model.json.chart.echart.JsonHtml;
import org.metachart.model.json.chart.echart.JsonLink;
import org.metachart.model.json.chart.echart.JsonOption;

import net.sf.exlp.util.io.JsonUtil;

public class JsonEchartFactory
{
	public enum Type{sankey,heatmap,heatbar}
	
	private final Writer w;
	private final JsonUtil jom;
	private String varChart; public String getVarChart() {return varChart;}	
	
	public static JsonEchartFactory instance(Writer writer, JsonUtil jom) {return new JsonEchartFactory(writer,jom);}
	private JsonEchartFactory(Writer writer, JsonUtil jom)
	{
		this.w=writer;
		this.jom=jom;
		varChart = "myChart";
	}
	
	public JsonEchartFactory varChart(String varChart) {this.varChart=varChart; return this;}
	
	public JsonEchartFactory declare(String divId, JsonHtml html) throws IOException
	{
		w.write("var dom = document.getElementById('"); w.write(divId); w.write("');");
		w.write("\n"); w.write("var "+varChart+" = echarts.init(dom, null, "+jom.toCompactString(html)+");");
		w.write("\n"); w.write("var option;");
		return this;
	}
	
	public JsonEchartFactory letData() throws IOException {w.write("\nlet data = [];"); return this;}
	public JsonEchartFactory letLinks() throws IOException {w.write("\nlet links = [];");return this;}
	public JsonEchartFactory letCategoriesX() throws IOException {w.write("\nlet xCategories = [];");return this;}
	public JsonEchartFactory letCategoriesY() throws IOException {w.write("\nlet yCategories = [];");return this;}
	
	public String option(JsonOption echart) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\noption = ").append(JsUtil.unQuote(jom.toFormattedString(echart)));
		w.write(sb.toString());
		return sb.toString();
	}
	
	public String data(List<JsonData> list) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\ndata = ").append(jom.toFormattedString(list));
		w.write(sb.toString());
		return sb.toString();
	}
	public String dataDoubles1(JsonData data) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\ndata = ").append(jom.toFormattedString(data.getDoubles1()));
		sb.append(";");
		w.write(sb.toString());
		return sb.toString();
	}
	
	public void dataDoubles2(JsonData data) throws IOException {this.dataDoubles2(data,null);}
	public void dataDoubles2(JsonData data, String transform) throws IOException
	{
		w.write("\n");
		w.write("\ndata = ");
		w.write(jom.toFormattedString(data.getDoubles2()));
		if(Objects.nonNull(transform))
		{
			w.write("\n");
			w.write(transform);
		}
		w.write(";");
	}
	
	public String categories(String axis, JsonData data) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\n").append(axis.toLowerCase());
		sb.append("Categories = ").append(jom.toFormattedString(data.getStrings()));
		sb.append(";");
		w.write(sb.toString());
		return sb.toString();
	}
	
	public String links(List<JsonLink> list) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\nlinks = ").append(jom.toFormattedString(list));
		w.write(sb.toString());
		return sb.toString();
	}
	
	public String init() throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\n").append(varChart).append(".setOption(option)");
		sb.append("\nwindow.addEventListener('resize', ").append(varChart).append(".resize);");
		w.write(sb.toString());
		return sb.toString();
	}
}