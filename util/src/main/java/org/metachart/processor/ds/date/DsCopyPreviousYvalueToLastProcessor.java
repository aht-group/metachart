package org.metachart.processor.ds.date;

import org.metachart.interfaces.McDatasetProcessor;
import org.metachart.xml.chart.Ds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DsCopyPreviousYvalueToLastProcessor implements McDatasetProcessor
{
	final static Logger logger = LoggerFactory.getLogger(DsCopyPreviousYvalueToLastProcessor.class);
	
	boolean activated;
	
	public DsCopyPreviousYvalueToLastProcessor()
	{
		
	}
	
	@Override public Ds process(Ds ds)
	{
		int max = ds.getData().size();
		if(ds.getData().size()>=2)
		{
			ds.getData().get(max-1).setY(ds.getData().get(max-2).getY());
		}
		return ds;
	}
}