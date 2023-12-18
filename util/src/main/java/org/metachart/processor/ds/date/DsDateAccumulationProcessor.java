package org.metachart.processor.ds.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.metachart.exception.McProcessingException;
import org.metachart.interfaces.McDatasetProcessor;
import org.metachart.xml.chart.Data;
import org.metachart.xml.chart.Ds;
import org.metachart.xml.chart.RendererTimeseries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DsDateAccumulationProcessor implements McDatasetProcessor
{
	final static Logger logger = LoggerFactory.getLogger(DsDateAccumulationProcessor.class);
	
	boolean activated;
	
	public DsDateAccumulationProcessor(boolean activated)
	{
		this.activated=activated;
	}
	
	public static DsDateAccumulationProcessor factory(RendererTimeseries renderer)
	{
		if(Objects.nonNull(renderer.isSumDate()))
		{
			if(renderer.isSumDate())
			{
				return new DsDateAccumulationProcessor(true);
			}
		}
		else
		{
			logger.warn("RendererTimeseries@sumDate not set. Defaulting to false");
		}
		return new DsDateAccumulationProcessor(false);
	}
	
	@Override public Ds process(Ds dataSet) throws McProcessingException
	{
		if(!activated){return dataSet;}
		
		List<Data> result = new ArrayList<Data>();
		
		for(Data data : dataSet.getData())
		{
			if(result.size()==0)
			{
				result.add(data);
			}
			else
			{
				Data previous = result.get(result.size()-1);
				if(previous.getRecord().compare(data.getRecord())==javax.xml.datatype.DatatypeConstants.GREATER)
				{
					StringBuilder sb = new StringBuilder();
					sb.append("The "+Data.class.getSimpleName()+" elements");
					sb.append(" in ").append(Ds.class.getSimpleName());
					sb.append(" need to be ordered ascending");
					sb.append(" Probably you have forgotten to first use ").append(DsRecordOrdererProcessor.class.getSimpleName());
					sb.append(" (previous: ").append(previous.getRecord());
					sb.append(" this:").append(data.getRecord()).append(")");
					throw new McProcessingException(sb.toString());
				}
				if(result.get(result.size()-1).getRecord().equals(data.getRecord()))
				{
					result.get(result.size()-1).setY(result.get(result.size()-1).getY()+data.getY());
				}
				else
				{
					result.add(data);
				}
			}
		}
		dataSet.getData().clear();
		dataSet.getData().addAll(result);
		return dataSet;
	}
}