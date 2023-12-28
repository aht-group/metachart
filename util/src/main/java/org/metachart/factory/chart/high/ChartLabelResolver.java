package org.metachart.factory.chart.high;

import java.util.Objects;

import org.metachart.factory.chart.high.OfxChartTypeResolver.AxisOrientation;
import org.metachart.model.xml.chart.Axis;
import org.metachart.model.xml.chart.Chart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChartLabelResolver
{	
	final static Logger logger = LoggerFactory.getLogger(ChartLabelResolver.class);
	
	public synchronized static String getTitle(Chart ofxChart)
	{
		String result = null;
		if(Objects.nonNull(ofxChart.getTitle())) {result = ofxChart.getTitle().getLabel();}
		return result;
	}
	
	public synchronized static String getAxisLabelX(Chart ofxChart){return getAxisLabel(ofxChart, AxisOrientation.domain);}
	public synchronized static String getAxisLabelY(Chart ofxChart){return getAxisLabel(ofxChart, AxisOrientation.range0);}
	
	public synchronized static String getAxisLabel(Chart ofxChart, AxisOrientation type)
	{
		String result = null;
		if(ofxChart!=null)
		{
			for(Axis axis : ofxChart.getAxis())
			{
				if(Objects.nonNull(axis.getCode()) && axis.getCode().equals(type.toString()) && Objects.nonNull(axis.getLabel()) && Objects.nonNull(axis.getLabel().getText()))
				{
					result = axis.getLabel().getText();
					break;
				}
			}
		}
		return result;
	}
}
