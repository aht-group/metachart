package org.metachart.factory.builder;

import org.metachart.processor.ds.DsAccumulatorProcessor;
import org.metachart.processor.ds.date.DsCopyPreviousYvalueToLastProcessor;
import org.metachart.processor.ds.date.DsDateAccumulationProcessor;
import org.metachart.processor.ds.date.DsRecordOrdererProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class McDsProcessorFactoryBuilder
{
	final static Logger logger = LoggerFactory.getLogger(McDsProcessorFactoryBuilder.class);
    
	public McDsProcessorFactoryBuilder()
	{
		
	}
	
	public DsDateAccumulationProcessor dateSummer()
	{
		return new DsDateAccumulationProcessor(true);
	}
	
	public DsRecordOrdererProcessor recordOrderer()
	{
		return new DsRecordOrdererProcessor(true);
	}
	
	public DsAccumulatorProcessor accumulator()
	{
		return new DsAccumulatorProcessor(true);
	}
	
	public DsCopyPreviousYvalueToLastProcessor copyPreviousYToLastRecord()
	{
		return new DsCopyPreviousYvalueToLastProcessor();
	}
}