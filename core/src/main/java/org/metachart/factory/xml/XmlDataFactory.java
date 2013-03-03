package org.metachart.factory.xml;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.metachart.factory.dataset.TimeSeriesDataSetFactory;
import org.metachart.xml.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlDataFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlDataFactory.class);
	
	public static Data build(double y)
	{
		Data data = new Data();
		data.setY(y);
		
		return data;
	}
	
	public static Data buildForYearMonth(double y, int year, int month)
	{
		Data xml = build(y);
		
		try
		{
			XMLGregorianCalendar xmlGc;
			xmlGc = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			xmlGc.setDay(1);
			xmlGc.setMonth(month);
			xmlGc.setYear(year);
			xmlGc.setHour(0);
			xmlGc.setMinute(0);
			xmlGc.setSecond(0);
			
			xml.setRecord(xmlGc);			
		}
		catch (DatatypeConfigurationException e) {e.printStackTrace();}

		return xml;
	}
}
