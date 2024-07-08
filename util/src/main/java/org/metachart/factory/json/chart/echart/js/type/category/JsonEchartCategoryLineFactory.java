package org.metachart.factory.json.chart.echart.js.type.category;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Objects;

import org.exlp.util.io.JsUtil;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonEdgeFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.grid.JsonGridFactory;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoGraph;
import org.metachart.factory.json.function.TxtEchartFunctionFactory;
import org.metachart.interfaces.chart.Data;
import org.metachart.interfaces.data.EchartGraphDataProvider;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.metachart.model.json.chart.echart.grid.JsonGrid;
import org.metachart.model.json.graph.mc.JsonCategory;
import org.metachart.model.json.graph.mc.JsonEdge;
import org.metachart.model.json.graph.mc.JsonGraph;
import org.metachart.model.json.graph.mc.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonEchartCategoryLineFactory implements EchartGraphDataProvider
{
	final static Logger logger = LoggerFactory.getLogger(JsonEchartCategoryLineFactory.class);
	
	private final Writer w;
	private String id; public JsonEchartCategoryLineFactory id(String id) {this.id=id; return this;}
	
	private JsonData categories; @Override public JsonData getGraphCategories() {return categories;}
	private JsonData nodes; @Override public JsonData getGraphNodes() {return nodes;}
	private JsonData edges; @Override public JsonData getGraphEdges() {return edges;}
	
	public static JsonEchartCategoryLineFactory instance() {return new JsonEchartCategoryLineFactory(null);}
	public static JsonEchartCategoryLineFactory instance(Writer w) {return new JsonEchartCategoryLineFactory(w);}
	private JsonEchartCategoryLineFactory(Writer w)
	{
		this.w=w;
		id="";
	}
	
	public void jsf(String div, JsonGrid grid, Data categories, Data data) throws IOException
	{	
		if(Objects.isNull(grid)) {grid = JsonGridFactory.instance().size("200",null).build();}
		
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(w,JsonUtil.instance()).id(div);

		jfEchart.declare(div,JsonHtmlFactory.build("svg",true,"100%",grid.getHeight()));
		jfEchart.letData().letCategoriesX();
		jfEchart.categories("X",categories.getValue());
		jfEchart.dataDoubles1(data.getValue());
		jfEchart.option(this.toOption());

		jfEchart.init();
	}
	
	public JsonOption toOption()
	{
		JsonOption option = new JsonOption();
		option.setAxisX(JsonAxisFactory.instance(id).type("category").data("categoriesX").build());
		option.setAxisY(JsonAxisFactory.instance().type("value").build());
		
		JsonSeries series = new JsonSeries();
		series.setType(JsonEchartFactory.Type.line.toString());
		series.setData(JsUtil.magicField("data"+id));
		
		option.setSeries(new ArrayList<>());
		option.getSeries().add(series);
		
		return option;
	}
	
}