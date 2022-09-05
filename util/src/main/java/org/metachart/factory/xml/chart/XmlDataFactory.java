package org.metachart.factory.xml.chart;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.metachart.xml.chart.Data;
import org.metachart.xml.chart.Ds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.DateUtil;

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
    public static Data category(String category,  double value)
    {
        return categoryLabel(category,null,value);
    }
    public static Data categoryLabel(String category,  String label, double value)
    {
        Data data = new Data();
        data.setCategory(category);
        data.setLabel(label);
        data.setY(value);
        return data;
    }
	
    public static Data build(long id, GregorianCalendar record, double value)
    {
    	Data xml = build(value,record);
    	xml.setId(id);
    	return xml;
    }
    public static Data build(double y, GregorianCalendar record) {return build(y,record.getTime());}
	public static Data build(double y, Date date) {return build(y, DateUtil.toXmlGc(date));}

	public static Data build(Date date)
	{
		Data xml = new Data();
		xml.setRecord(DateUtil.toXmlGc(date));
		return xml;
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
	
	public static void append(Ds ds, boolean recursive, Date record, Double value)
	{
		ds.getData().add(XmlDataFactory.build(record));
		if(recursive) {for(Ds child : ds.getDs()) {XmlDataFactory.append(child,recursive,record,value);}}
	}
}
