package org.metachart.processor.ds.date;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

import org.metachart.interfaces.McDatasetProcessor;
import org.metachart.model.xml.chart.Data;
import org.metachart.model.xml.chart.Ds;
import org.metachart.model.xml.chart.RendererTimeseries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DsRecordOrdererProcessor implements McDatasetProcessor
{
	final static Logger logger = LoggerFactory.getLogger(DsRecordOrdererProcessor.class);
	
	boolean activated;
	
	public DsRecordOrdererProcessor(boolean activated)
	{
		this.activated=activated;
	}
	
	public static DsRecordOrdererProcessor factory(RendererTimeseries renderer)
	{
		if(Objects.nonNull(renderer.isOrderRecords()))
		{
			if(renderer.isOrderRecords())
			{
				return new DsRecordOrdererProcessor(true);
			}
		}
		else
		{
			logger.warn("RendererTimeseries@orderRecords not set. Defaulting to false");
		}
		return new DsRecordOrdererProcessor(false);
	}
	
	@Override public Ds process(Ds dataSet)
	{
		if(!activated){return dataSet;}
		Collections.sort(dataSet.getData(), new RecordComparator());
		return dataSet;
	}
	
	private class RecordComparator implements Comparator<Data>
	{
		public int compare(Data a, Data b)
		{
			return a.getRecord().compare(b.getRecord());
	    }
	}
}