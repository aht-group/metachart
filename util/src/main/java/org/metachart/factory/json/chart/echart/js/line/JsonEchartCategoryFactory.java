package org.metachart.factory.json.chart.echart.js.line;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Objects;

import org.exlp.util.io.JsUtil;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.grid.JsonGridFactory;
import org.metachart.interfaces.chart.Data;
import org.metachart.interfaces.data.EchartGraphDataProvider;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.metachart.model.json.chart.echart.grid.JsonGrid;
import org.metachart.util.provider.data.EchartLineCategoryDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonEchartCategoryFactory implements EchartGraphDataProvider
{
	final static Logger logger = LoggerFactory.getLogger(JsonEchartCategoryFactory.class);
	
	private final Writer w;
	private String id; public JsonEchartCategoryFactory id(String id) {this.id=id; return this;}
	
	private JsonData categories; @Override public JsonData getGraphCategories() {return categories;}
	private JsonData nodes; @Override public JsonData getGraphNodes() {return nodes;}
	private JsonData edges; @Override public JsonData getGraphEdges() {return edges;}
	
	public static JsonEchartCategoryFactory instance() {return new JsonEchartCategoryFactory(null);}
	public static JsonEchartCategoryFactory instance(Writer w) {return new JsonEchartCategoryFactory(w);}
	private JsonEchartCategoryFactory(Writer w)
	{
		this.w=w;
		id="";
	}
	
	public void jsf(String div, JsonGrid grid, EchartLineCategoryDataProvider provider) throws IOException
	{	
		if(Objects.isNull(grid)) {grid = JsonGridFactory.instance().size("400",null).assemble();}
		
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(w,JsonUtil.instance()).id(div);

		jfEchart.declare(div,JsonHtmlFactory.instance().assemble());
		jfEchart.letData().letCategoriesX();
		
		if(Objects.nonNull(provider.getCategories())) {jfEchart.categories("X",provider.getCategories());}
		if(Objects.nonNull(provider.getData())) {jfEchart.dataDoubles1(provider.getData());}
		
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