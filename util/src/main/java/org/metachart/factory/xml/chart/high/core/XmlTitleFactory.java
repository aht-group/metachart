package org.metachart.factory.xml.chart.high.core;

import org.metachart.xml.chart.Title;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlTitleFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlTitleFactory.class);
	
	public static Title build(String title)
	{
		Title xml = new Title();
		xml.setLabel(title);
		return xml;
	}
}