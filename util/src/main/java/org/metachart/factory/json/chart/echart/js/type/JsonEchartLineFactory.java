package org.metachart.factory.json.chart.echart.js.type;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Objects;

import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonEdgeFactory;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoGraph;
import org.metachart.interfaces.chart.Data;
import org.metachart.interfaces.data.EchartGraphDataProvider;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.grid.JsonGrid;
import org.metachart.model.json.graph.mc.JsonCategory;
import org.metachart.model.json.graph.mc.JsonEdge;
import org.metachart.model.json.graph.mc.JsonGraph;
import org.metachart.model.json.graph.mc.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonEchartLineFactory implements EchartGraphDataProvider
{
	final static Logger logger = LoggerFactory.getLogger(JsonEchartLineFactory.class);
	
	private final Writer w;
	private String id; public JsonEchartLineFactory id(String id) {this.id=id; return this;}
	
	private JsonData categories; @Override public JsonData getGraphCategories() {return categories;}
	private JsonData nodes; @Override public JsonData getGraphNodes() {return nodes;}
	private JsonData edges; @Override public JsonData getGraphEdges() {return edges;}
	
	public static JsonEchartLineFactory instance() {return new JsonEchartLineFactory(null);}
	public static JsonEchartLineFactory instance(Writer w) {return new JsonEchartLineFactory(w);}
	private JsonEchartLineFactory(Writer w)
	{
		this.w=w;
		id="";
	}
	

}