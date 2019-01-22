package org.metachart.factory.xml.chart;

import org.metachart.xml.chart.DataSet;
import org.metachart.xml.chart.Ds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlDataSetFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlDataSetFactory.class);
	
	public static DataSet build(DataSet ds)
	{
		DataSet xml = build();
		xml.getDataSet().add(ds);
		return xml;
	}
	
	public static Ds build2(Ds ds)
	{
		Ds xml = build2();
		xml.getDs().add(ds);
		return xml;
	}
	
	public static DataSet build()
	{
		return new DataSet();
	}
	
	public static Ds build2()
	{
		return new Ds();
	}
}