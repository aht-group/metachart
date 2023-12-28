package org.metachart.factory.xml.chart.high.ds;

import java.util.List;

import org.metachart.model.xml.chart.Data;
import org.metachart.model.xml.chart.Ds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeSeriesUtil
{	
	final static Logger logger = LoggerFactory.getLogger(TimeSeriesUtil.class);
	
	public static Data getLastRecordFromOderedDataSets(List<Ds> list)
	{
		Data result = null;
		for(Ds ds : list)
		{
			Data last = ds.getData().get(ds.getData().size()-1);
			if(result == null){result = last;}
			else
			{
				if(result.getRecord().toGregorianCalendar().before(last.getRecord().toGregorianCalendar()))
				{
					result = last;
				}
			}
		}
		return result;
	}
}
