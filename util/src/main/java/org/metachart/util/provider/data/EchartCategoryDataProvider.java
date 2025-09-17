package org.metachart.util.provider.data;

import org.metachart.interfaces.chart.Data;
import org.metachart.model.json.chart.echart.data.JsonData;

public class EchartCategoryDataProvider
{
	private JsonData categories; public JsonData getCategories(){return categories;}
	private JsonData data; public JsonData getData() {return data;}
	
	
	public static EchartCategoryDataProvider instance()
	{
		EchartCategoryDataProvider p = new EchartCategoryDataProvider();
		
		return p;
	}
	
	private EchartCategoryDataProvider()
	{
		
	}
	
	public EchartCategoryDataProvider categories(JsonData categories) {this.categories=categories; return this;}
	public EchartCategoryDataProvider data(JsonData data) {this.data=data; return this;}
	
	public EchartCategoryDataProvider categories(Data categories) {this.categories=categories.getValue(); return this;}
	public EchartCategoryDataProvider data(Data data) {this.categories=data.getValue(); return this;}
	
}