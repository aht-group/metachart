package org.metachart.factory.xml.chart;

import org.metachart.xml.chart.Chart;
import org.metachart.xml.chart.Title;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlChartFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlChartFactory.class);
	
	public static Chart build(){return new Chart();}
	
	public static Chart build(Title title)
	{
		Chart xml = build();
		xml.setTitle(title);
		return xml;
	}
}