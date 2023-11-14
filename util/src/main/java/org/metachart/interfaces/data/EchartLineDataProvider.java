package org.metachart.interfaces.data;

import org.metachart.model.json.chart.echart.data.JsonData;

public interface EchartLineDataProvider
{
	JsonData getLineCategories();
	JsonData getLineData();
}