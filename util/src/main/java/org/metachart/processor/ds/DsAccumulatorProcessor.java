package org.metachart.processor.ds;

import org.metachart.interfaces.McDatasetProcessor;
import org.metachart.xml.chart.Data;
import org.metachart.xml.chart.Ds;
import org.metachart.xml.chart.RendererTimeseries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DsAccumulatorProcessor implements McDatasetProcessor
{
	final static Logger logger = LoggerFactory.getLogger(DsAccumulatorProcessor.class);
	
	boolean activated;
	
	public DsAccumulatorProcessor(boolean activated)
	{
		this.activated=activated;
	}
	
	public static DsAccumulatorProcessor factory(RendererTimeseries renderer)
	{
		if(renderer.isSetCumulate())
		{
			if(renderer.isCumulate()){return new DsAccumulatorProcessor(true);}
		}
		else
		{
			logger.warn("RendererTimeseries@Cumulate not set. Defaulting to false");
		}
		return new DsAccumulatorProcessor(false);
	}
		
	@Override public Ds process(Ds ds)
	{
		if(!activated){return ds;}
		
		double value=0;
		for(Data data : ds.getData())
		{
			data.setY(data.getY()+value);
			value=data.getY();
		}
		
		return ds;
	}
}