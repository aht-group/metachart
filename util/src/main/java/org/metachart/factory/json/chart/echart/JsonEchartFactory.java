package org.metachart.factory.json.chart.echart;

import java.io.IOException;
import java.io.Writer;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import org.exlp.util.io.JsUtil;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.txt.chart.TxtDataFactory;
import org.metachart.model.json.chart.echart.JsonHtml;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonEdge;
import org.metachart.model.json.chart.echart.data.JsonLink;

public class JsonEchartFactory
{
	public enum Type{line,time,
					heatmap,heatbar,
					sankey,graph,gauge}

	private final Writer w;
	private final JsonUtil jom;

	private String varChart; public String getVarChart() {return varChart;}	
	private String id; public JsonEchartFactory id(String id) {this.id=id; return this;}


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
		sb.append("\n").append("$(function() {");
		sb.append("\n").append("    var td = $('#").append(div).append("').closest('td');");
		sb.append("\n").append("    var index = td.parent().children().index(td);");
		sb.append("\n").append("    td.closest('table').find('th:nth-child(' + (index + 1) +'),td:nth-child(' + (index + 1) +')').addClass('jeesl-min-width-column');");
		sb.append("\n").append("});");

		sb.append("\n").append("var ").append(varChart).append(id).append(" = ");
		sb.append("echarts.init(").append("document.getElementById('").append(div).append("'), null, "+jom.toCompactString(html)+");");
		sb.append("\n").append("var option").append(id).append(";");
		w.write(sb.toString());
		return this;
	}

	public JsonEchartFactory letData() throws IOException {return this.letData(null);}
	public JsonEchartFactory letData(String nr) throws IOException {w.write("\nlet "+TxtDataFactory.dataId(id,nr)+" = [];"); return this;}
	public JsonEchartFactory letLinks() throws IOException {w.write("\nlet links"+id+" = [];");return this;}
	public JsonEchartFactory letEdges() throws IOException {w.write("\nlet edges"+id+" = [];");return this;}
	public JsonEchartFactory letCategories(String suffix) throws IOException {w.write("\nlet categories"+suffix+id+" = [];");return this;}
	public JsonEchartFactory letCategoriesX() throws IOException {w.write("\nlet categoriesX"+id+" = [];");return this;}
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

	public String dataTime(JsonData data) throws IOException
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\n").append(TxtDataFactory.dataId(id,data.getId())).append(" = [");
		for(int i=0; i<data.getTimes().length; i++)
		{
			sb.append(" [");
//			sb.append(data.getTimes()[i].atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
//			sb.append(data.getTimes()[i].toString());
			sb.append("\"").append(data.getTimes()[i].format(formatter)).append("\"");
			sb.append(" , ").append(data.getDoubles1()[i]);
			sb.append("]");
			if(i<data.getTimes().length-1) {sb.append(", ");}
		}
		sb.append("];");
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