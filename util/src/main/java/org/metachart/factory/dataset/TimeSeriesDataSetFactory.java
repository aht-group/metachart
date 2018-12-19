package org.metachart.factory.dataset;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.metachart.factory.xml.chart.XmlDataFactory;
import org.metachart.xml.chart.Data;
import org.metachart.xml.chart.DataSet;
import org.metachart.xml.chart.Ds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeSeriesDataSetFactory
{
	final static Logger logger = LoggerFactory.getLogger(TimeSeriesDataSetFactory.class);
	
	private Map<Object,List<Data>> map;
	private List<Object> list;

	public TimeSeriesDataSetFactory()
	{
		map = new Hashtable<Object,List<Data>>();
		list = new ArrayList<Object>();
	}
	
	public void add(Object id, double value, int year, int month)
	{
		getList(id).add(XmlDataFactory.buildForYearMonth(value, year, month));

	}
	
	public void add(Object id, double value, Date date)
	{
		logger.info(""+id);
		
	}
	
	@Deprecated
	public DataSet build(Object id)
	{
		DataSet ds = new DataSet();
		ds.getData().addAll(map.get(id));
		return ds;
	}
	
	public Ds buildDs(Object id)
	{
		Ds ds = new Ds();
		ds.getData().addAll(map.get(id));
		return ds;
	}
	
	private List<Data> getList(Object id)
	{
		if(!map.containsKey(id))
		{
			list.add(id);
			map.put(id, new ArrayList<Data>());
		}
		return map.get(id);
	}
	
	public List<Object> getList() {return list;}
}