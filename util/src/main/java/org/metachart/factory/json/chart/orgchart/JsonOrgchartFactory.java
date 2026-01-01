package org.metachart.factory.json.chart.orgchart;

import java.io.IOException;
import java.io.Writer;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import org.exlp.util.io.JsUtil;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.orgchart.js.demo.OrgchartDemo;
import org.metachart.factory.txt.chart.TxtDataFactory;
import org.metachart.model.json.chart.echart.JsonHtml;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonEdge;
import org.metachart.model.json.chart.echart.data.JsonLink;
import org.metachart.model.json.chart.orgchart.data.JsonOptions;

public class JsonOrgchartFactory
{

	private final Writer w;
	private final JsonUtil jom;

//	private String varChart; public String getVarChart() {return varChart;}	
	private String id; public JsonOrgchartFactory id(String id) {this.id=id; return this;}


	public static JsonOrgchartFactory instance(Writer writer, JsonUtil jom) {return new JsonOrgchartFactory(writer,jom);}
	private JsonOrgchartFactory(Writer writer, JsonUtil jom)
	{
		this.w=writer;
		this.jom=jom;
		id="";
//		varChart = "metaChart";
	}

	public JsonOrgchartFactory declare() throws IOException
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("var options").append(id).append("= {};");
		w.write(sb.toString());
		
		return this;
	}

//	public JsonOrgchartFactory letData() throws IOException {return this.letData(null);}
//	public JsonOrgchartFactory letData(String nr) throws IOException {w.write("\nlet "+TxtDataFactory.dataId(id,nr)+" = [];"); return this;}
//	public JsonOrgchartFactory letLinks() throws IOException {w.write("\nlet links"+id+" = [];");return this;}
//	public JsonOrgchartFactory letEdges() throws IOException {w.write("\nlet edges"+id+" = [];");return this;}
//	public JsonOrgchartFactory letCategory(String suffix) throws IOException {w.write("\nlet category"+suffix+id+" = [];");return this;}

	public JsonOrgchartFactory options(JsonOptions options) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n\n");
		sb.append("options").append(id).append(" = ").append(JsUtil.unQuote(jom.toFormattedString(options)));
		w.write(sb.toString());
		
		return this;
	}

	public JsonOrgchartFactory init() throws IOException
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n\n");
		sb.append("$('#").append(id).append("').orgchart(options").append(id).append(");");
		w.write(sb.toString());
		
		return this;
	}
	
	public void json() throws IOException
	{
		declare().options(OrgchartDemo.toOptions()).init();
	}
}