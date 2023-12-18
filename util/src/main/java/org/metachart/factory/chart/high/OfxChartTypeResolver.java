package org.metachart.factory.chart.high;

import java.util.Objects;

import org.metachart.xml.chart.AxisType;
import org.metachart.xml.chart.Renderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.xml.JaxbUtil;

public class OfxChartTypeResolver
{
	final static Logger logger = LoggerFactory.getLogger(OfxChartTypeResolver.class);
	
	public static enum Type{TimeSeries, TimeBar, Bar, Gantt, Spline};
	
	public static enum AxisOrientation{domain,range0,range1};
	public static enum ChartAxisType{Nil,Number,Date};
	
	public synchronized static Type getType(Renderer type)
	{
		if(Objects.nonNull(type.getRendererTimeseries())) {return Type.TimeSeries;}
		if(Objects.nonNull(type.getBar())) {return Type.Bar;}
		if(Objects.nonNull(type.getTimebar())) {return Type.TimeBar;}
		if(Objects.nonNull(type.getGantt())) {return Type.Gantt;}
		if(Objects.nonNull(type.getSpline())) {return Type.Spline;}
		logger.warn("Unknown Charttype");
		JaxbUtil.debug(type);
		return null;
	}
	
	public synchronized static ChartAxisType getAxisType(AxisType type)
	{
		if(type!=null)
		{
			if(Objects.nonNull(type.getNumber())) {return ChartAxisType.Number;}
			if(Objects.nonNull(type.getDate())) {return ChartAxisType.Date;}
		}
		return ChartAxisType.Nil;
	}
}
