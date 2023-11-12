package org.metachart.interfaces.graph;

import org.metachart.model.json.chart.echart.data.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface EchartDataProvider
{
	final static Logger logger = LoggerFactory.getLogger(EchartDataProvider.class);

	JsonData getGraphCategories();
	JsonData getGraphNodes();
	JsonData getGraphEdges();
}