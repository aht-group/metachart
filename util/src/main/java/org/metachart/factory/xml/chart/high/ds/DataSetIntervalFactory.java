package org.metachart.factory.xml.chart.high.ds;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.metachart.factory.xml.chart.high.core.XmlDataFactory;
import org.metachart.model.xml.chart.Data;
import org.metachart.model.xml.chart.Ds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSetIntervalFactory
{
	final static Logger logger = LoggerFactory.getLogger(DataSetIntervalFactory.class);
	
	private Map<Object,List<Data>> map;
	private List<Object> list;

	public DataSetIntervalFactory()
	{

	}
	
	public void add(Object id, double value, int year, int month)
	{
		getList(id).add(XmlDataFactory.buildForYearMonth(value, year, month));

	}
	
	public void add(Object id, double value, Date date)
	{
		logger.info(""+id);
		
	}
	
	public Ds build(Object id)
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