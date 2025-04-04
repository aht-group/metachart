package org.metachart.util.provider.data;

import org.metachart.interfaces.chart.Data;
import org.metachart.model.json.chart.echart.data.JsonData;

public class EchartLineCategoryDataProvider
{
	private JsonData categories; public JsonData getCategories(){return categories;}
	private JsonData data; public JsonData getData() {return data;}
	
	
	public static EchartLineCategoryDataProvider instance()
	{
		EchartLineCategoryDataProvider p = new EchartLineCategoryDataProvider();
		
		return p;
	}
	
	private EchartLineCategoryDataProvider()
	{
		
	}
	
	public EchartLineCategoryDataProvider categories(JsonData categories) {this.categories=categories; return this;}
	public EchartLineCategoryDataProvider data(JsonData data) {this.data=data; return this;}
	
	public EchartLineCategoryDataProvider categories(Data categories) {this.categories=categories.getValue(); return this;}
	public EchartLineCategoryDataProvider data(Data data) {this.categories=data.getValue(); return this;}
	
}