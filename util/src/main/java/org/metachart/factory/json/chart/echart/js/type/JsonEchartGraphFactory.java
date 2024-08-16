package org.metachart.factory.json.chart.echart.js.type;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonEdgeFactory;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoGraph;
import org.metachart.factory.xhtml.XhtmlEchartFactory;
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

public class JsonEchartGraphFactory implements EchartGraphDataProvider
{
	final static Logger logger = LoggerFactory.getLogger(JsonEchartGraphFactory.class);
	
	private final Writer w;
	private String id; public JsonEchartGraphFactory id(String id) {this.id=id; return this;}
	
	private JsonData categories; @Override public JsonData getGraphCategories() {return categories;}
	private JsonData nodes; @Override public JsonData getGraphNodes() {return nodes;}
	private JsonData edges; @Override public JsonData getGraphEdges() {return edges;}
	
	public static JsonEchartGraphFactory instance() {return new JsonEchartGraphFactory(null);}
	public static JsonEchartGraphFactory instance(Writer w) {return new JsonEchartGraphFactory(w);}
	private JsonEchartGraphFactory(Writer w)
	{
		this.w=w;
		id="";
	}
	
	public void jsf(String div, JsonGrid jsfGrid, Data categories, Data data, Data edges) throws IOException
	{		
		JsonEchartFactory f = JsonEchartFactory.instance(w,JsonUtil.instance()).id(div);
		EchartDemoGraph demo = EchartDemoGraph.instance(f).id(id);
		
		f.declare(div,JsonHtmlFactory.build("canvas",false));
		f.categories("Node",categories.getValue().getData());
		f.data(data.getValue().getData());
		f.edges(edges.getValue().getEdges());
		f.option(demo.demoOption());
		
		f.init();
	}
	
	public JsonEchartGraphFactory transform(JsonGraph graph)
	{
		if(Objects.isNull(graph.getCategories())) {graph.setCategories(new ArrayList<>());}
		if(Objects.isNull(graph.getNodes())) {graph.setNodes(new ArrayList<>());}
		if(Objects.isNull(graph.getEdges())) {graph.setEdges(new ArrayList<>());}
		
		JsonDataFactory jfNodes = JsonDataFactory.instance();
		for(JsonNode node : graph.getNodes())
		{
			jfNodes.data(JsonDataFactory.instance().name(node.getLabel()).category(graph.getCategories().indexOf(node.getCategory())).build());
		}
		nodes = jfNodes.build();
		
		JsonDataFactory jfEdges = JsonDataFactory.instance();
		for(JsonEdge edge : graph.getEdges())
		{
			int source = graph.getNodes().indexOf(edge.getSource());
			int destination = graph.getNodes().indexOf(edge.getDestination());		
			jfEdges.edge(JsonEdgeFactory.edge(source,destination));	
		}
		edges = jfEdges.build();
		
		JsonDataFactory jfCategory = JsonDataFactory.instance();
		for(JsonCategory category : graph.getCategories())
		{
			jfCategory.data(JsonDataFactory.build(category.getLabel()));
		}
		categories = jfCategory.build();
		
		return this;
	}
	
	public void xhtml(Path path, EchartGraphDataProvider data) throws IOException
	{
		StringWriter w = new StringWriter();
		XhtmlEchartFactory xf = XhtmlEchartFactory.instance();
		JsonEchartFactory fEchart = JsonEchartFactory.instance(w,JsonUtil.instance()).declare(xf.getDivCntainerId(),JsonHtmlFactory.build("canvas",false));
		EchartDemoGraph demo = EchartDemoGraph.instance(fEchart);
		
//		fEchart.letCategories("Node").letData().letEdges();
//		fEchart.categories("Node",data.getGraphCategories().getData());
//		fEchart.data(data.getGraphNodes().getData());
//		fEchart.edges(data.getGraphEdges().getEdges());
//		fEchart.option(demo.demoOption());
//		fEchart.init();
		
//		xf.write(path,w);
	}
}