package org.metachart.interfaces.data;

import org.metachart.model.json.chart.echart.data.JsonData;

public interface EchartGraphDataProvider
{
	JsonData getGraphCategories();
	JsonData getGraphNodes();
	JsonData getGraphEdges();
}