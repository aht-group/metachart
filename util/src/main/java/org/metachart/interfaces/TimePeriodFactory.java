package org.metachart.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimePeriodFactory
{	
	final static Logger logger = LoggerFactory.getLogger(TimePeriodFactory.class);
	
	public static enum OfxChartTimePeriod {Hour,Day,Month,Year};
}