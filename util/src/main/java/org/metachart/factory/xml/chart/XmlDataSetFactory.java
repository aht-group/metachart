package org.metachart.factory.xml.chart;

import org.metachart.xml.chart.Ds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlDataSetFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlDataSetFactory.class);
	
	public static Ds build(Ds ds)
	{
		Ds xml = build();
		xml.getDs().add(ds);
		return xml;
	}
	
	public static Ds build()
	{
		return new Ds();
	}
}