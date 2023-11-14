package org.metachart.factory.json.chart.echart;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Objects;

import org.exlp.util.io.JsUtil;
import org.exlp.util.io.JsonUtil;
import org.metachart.model.json.chart.echart.JsonHtml;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonEdge;
import org.metachart.model.json.chart.echart.data.JsonLink;

public class JsonEchartFactory
{
	public enum Type{line,sankey,heatmap,heatbar,graph}
	
	private final Writer w;
	private final JsonUtil jom;
	private String varChart; public String getVarChart() {return varChart;}	
	private String id; public JsonEchartFactory id(String id) {this.id=id ;return this;}
	
	public static JsonEchartFactory instance(Writer writer, JsonUtil jom) {return new JsonEchartFactory(writer,jom);}
	private JsonEchartFactory(Writer writer, JsonUtil jom)
	{
		this.w=writer;
		this.jom=jom;
		id="";
		varChart = "metaChart";
	}
	
	public JsonEchartFactory declare(String div, JsonHtml html) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n").append("var ").append(varChart).append(id).append(" = ");
		sb.append("echarts.init(").append("document.getElementById('").append(div).append("'), null, "+jom.toCompactString(html)+");");
		sb.append("\n").append("var option").append(id).append(";");
		w.write(sb.toString());
		return this;
	}
	
	public JsonEchartFactory letData() throws IOException {w.write("\nlet data"+id+" = [];"); return this;}
	public JsonEchartFactory letLinks() throws IOException {w.write("\nlet links"+id+" = [];");return this;}
	public JsonEchartFactory letEdges() throws IOException {w.write("\nlet edges"+id+" = [];");return this;}
	public JsonEchartFactory letCategories(String suffix) throws IOException {w.write("\nlet categories"+suffix+id+" = [];");return this;}
	public JsonEchartFactory letCategoriesX() throws IOException {w.write("\nlet xCategories"+id+" = [];");return this;}
	public JsonEchartFactory letCategoriesY() throws IOException {w.write("\nlet yCategories"+id+" = [];");return this;}
	
	public void option(JsonOption echart) throws IOException
	{
		w.write("\n");
		w.write("\noption");
		w.write(id);
		w.write("= ");
		w.write(JsUtil.unQuote(jom.toFormattedString(echart)));
	}
	
	public String data(List<JsonData> list) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\ndata").append(id).append(" = ");
		sb.append(jom.toFormattedString(list));
		sb.append(";");
		w.write(sb.toString());
		return sb.toString();
	}
	
	
	public String dataDoubles1(JsonData data) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\ndata").append(id).append(" = ");
		sb.append(jom.toFormattedString(data.getDoubles1()));
		sb.append(";");
		w.write(sb.toString());
		return sb.toString();
	}
	
	public void dataDoubles2(JsonData data) throws IOException {this.dataDoubles2(data,null);}
	public void dataDoubles2(JsonData data, String transform) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\ndata").append(id).append(" = ");
		sb.append(jom.toFormattedString(data.getDoubles2()));
		if(Objects.nonNull(transform))
		{
			sb.append("\n").append(transform);
		}
		sb.append(";");
		w.write(sb.toString());
	}
	
	public String categories(String suffix, JsonData data) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\n").append("categories").append(suffix).append(id);
		sb.append(" = ").append(jom.toFormattedString(data.getStrings()));
		sb.append(";");
		w.write(sb.toString());
		return sb.toString();
	}
	public String categories(String suffix, List<JsonData> data) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\n").append("categories").append(suffix).append(id);
		sb.append(" = ").append(jom.toFormattedString(data));
		sb.append(";");
		w.write(sb.toString());
		return sb.toString();
	}
	
	public String links(List<JsonLink> list) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\nlinks").append(id);
		sb.append(" = ").append(jom.toFormattedString(list));
		sb.append(";");
		w.write(sb.toString());
		return sb.toString();
	}
	public String edges(List<JsonEdge> list) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\nedges").append(id);
		sb.append(" = ").append(jom.toFormattedString(list));
		sb.append(";");
		w.write(sb.toString());
		return sb.toString();
	}
	
	public void init() throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\n").append(varChart).append(id).append(".setOption(option").append(id).append(");");
		sb.append("\nwindow.addEventListener('resize', ").append(varChart).append(id).append(".resize);");
		w.write(sb.toString());
	}
}