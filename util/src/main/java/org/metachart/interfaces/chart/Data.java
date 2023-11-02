package org.metachart.interfaces.chart;

import org.metachart.model.json.chart.echart.data.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Data
{
	final static Logger logger = LoggerFactory.getLogger(Data.class);

	JsonData getValue();
}