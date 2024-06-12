package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.util.ArrayList;

import org.exlp.util.io.JsUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonEdgeFactory;
import org.metachart.factory.json.chart.echart.ui.JsonTooltipFactory;
import org.metachart.interfaces.data.EchartGraphDataProvider;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchartDemoGraph implements EchartGraphDataProvider
{
	final static Logger logger = LoggerFactory.getLogger(EchartDemoGraph.class);
	
	private final JsonEchartFactory jfEchart;
	
	private String id; public EchartDemoGraph id(String id) {this.id=id; return this;}
	
	public static EchartDemoGraph instance(JsonEchartFactory fEchart) {return new EchartDemoGraph(fEchart);}
	private EchartDemoGraph(JsonEchartFactory jfEchart)
	{
		this.jfEchart=jfEchart;
		id="";
	}
	
	public void demo() throws IOException
	{
		jfEchart.letCategories("Node").letData().letEdges();
		jfEchart.categories("Node",this.getGraphCategories().getData());
		jfEchart.data(this.getGraphNodes().getData());
		jfEchart.edges(this.getGraphEdges().getEdges());
		jfEchart.option(this.demoOption());
	}
	public JsonOption demoOption()
	{
		JsonOption option = new JsonOption();
			
		option.setTooltip(JsonTooltipFactory.instance().position("top").build());
		option.setSeries(new ArrayList<>());
		
		JsonSeries series = new JsonSeries();
		series.setType(JsonEchartFactory.Type.graph.toString());
		series.setLayout("force");
		series.setAnimation(true);
		series.setDraggable(true);
		series.setCategories(JsUtil.magicField("categoriesNode"+id));
		series.setData(JsUtil.magicField("data"+id));
		series.setEdges(JsUtil.magicField("edges"+id));
		
		
		option.getSeries().add(series);
		return option;
	}
	
	@Override public JsonData getGraphCategories()
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		
		jf.data(JsonDataFactory.instance().name("Cat A").build());
		jf.data(JsonDataFactory.instance().name("Cat B").build());
		
		return jf.build();
	}
	
	@Override public JsonData getGraphNodes()
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		jf.data(JsonDataFactory.instance().name("Node 1").category(0).build());
		jf.data(JsonDataFactory.instance().name("Node 2").category(1).build());
		jf.data(JsonDataFactory.instance().name("Node 3").category(1).build());
		jf.data(JsonDataFactory.instance().name("Node 4").category(1).build());
		return jf.build();
	}
	
	@Override public JsonData getGraphEdges()
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		jf.edge(JsonEdgeFactory.edge(0,1));
		jf.edge(JsonEdgeFactory.edge(0,2));
		jf.edge(JsonEdgeFactory.edge(0,3));
		jf.edge(JsonEdgeFactory.edge(0,4));
		jf.edge(JsonEdgeFactory.edge(2,3));
		return jf.build();
	}
	
	
}