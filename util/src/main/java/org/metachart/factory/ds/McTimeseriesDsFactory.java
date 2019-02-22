package org.metachart.factory.ds;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.metachart.exception.McProcessingException;
import org.metachart.factory.xml.chart.XmlDataFactory;
import org.metachart.factory.xml.chart.XmlDataSetFactory;
import org.metachart.interfaces.McDatasetProcessor;
import org.metachart.xml.chart.Data;
import org.metachart.xml.chart.Ds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class McTimeseriesDsFactory <T extends Object>
{
	final static Logger logger = LoggerFactory.getLogger(McTimeseriesDsFactory.class);
	
	private final List<McDatasetProcessor> processors; public void addProcessor(McDatasetProcessor processor) {processors.add(processor);}
	
	private Map<T,List<Data>> map;
	private List<T> list; public List<T> getList() {return list;}

	public McTimeseriesDsFactory()
	{
		map = new Hashtable<T,List<Data>>();
		list = new ArrayList<T>();
		processors = new ArrayList<McDatasetProcessor>();
	}
	
	public void add(T t, double value, int year, int month)
	{
		getList(t).add(XmlDataFactory.buildForYearMonth(value, year, month));
	}
	
	public void add(Object id, double value, Date date)
	{
		logger.info(""+id);
		
	}
	
	public Ds build(T t) throws McProcessingException
	{
		Ds ds = XmlDataSetFactory.build(map.get(t));
		for(McDatasetProcessor processor : processors)
		{
			ds = processor.process(ds);
		}
		
		return ds;
	}
	
	private List<Data> getList(T t)
	{
		if(!map.containsKey(t))
		{
			list.add(t);
			map.put(t, new ArrayList<Data>());
		}
		return map.get(t);
	}
}