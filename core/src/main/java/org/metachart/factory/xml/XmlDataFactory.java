package org.metachart.factory.xml;

import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
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

    public static Data build(String category)
    {
        Data data = new Data();
        data.setCategory(category);
        return data;
    }
	
	public static Data build(double y, Date date)
	{
		try
		{
			DateTime dt = new DateTime(date);
			XMLGregorianCalendar xmlGc = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			xmlGc.setDay(dt.getDayOfMonth());
			xmlGc.setMonth(dt.getMonthOfYear());
			xmlGc.setYear(dt.getYear());
			xmlGc.setHour(dt.getHourOfDay());
			xmlGc.setMinute(dt.getMinuteOfHour());
			xmlGc.setSecond(dt.getSecondOfMinute());
			return build(y, xmlGc);		
		}
		catch (DatatypeConfigurationException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static Data build(Date date)
	{
		try
		{
			DateTime dt = new DateTime(date);
			XMLGregorianCalendar xmlGc = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			xmlGc.setDay(dt.getDayOfMonth());
			xmlGc.setMonth(dt.getMonthOfYear());
			xmlGc.setYear(dt.getYear());
			xmlGc.setHour(dt.getHourOfDay());
			xmlGc.setMinute(dt.getMinuteOfHour());
			xmlGc.setSecond(dt.getSecondOfMinute());
			Data xml = new Data();
			xml.setRecord(xmlGc);
			return xml;
		}
		catch (DatatypeConfigurationException e)
		{
			e.printStackTrace();
			return null;
		}

	}
	
	public static Data buildForYearMonth(double y, int year, int month)
	{
		try
		{
			XMLGregorianCalendar xmlGc = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			xmlGc.setDay(1);
			xmlGc.setMonth(month);
			xmlGc.setYear(year);
			xmlGc.setHour(0);
			xmlGc.setMinute(0);
			xmlGc.setSecond(0);
			
			return build(y, xmlGc);		
		}
		catch (DatatypeConfigurationException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static Data build(double y, XMLGregorianCalendar record)
	{
		Data xml = build(y);
		xml.setRecord(record);
		return xml;
	}
}
