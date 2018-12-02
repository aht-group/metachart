package org.metachart.factory.xml.chart;

import org.metachart.xml.chart.Subtitle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlSubtitleFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlSubtitleFactory.class);
	
	public static Subtitle build(String title)
	{
		Subtitle xml = new Subtitle();
		xml.setLabel(title);
		return xml;
	}
}