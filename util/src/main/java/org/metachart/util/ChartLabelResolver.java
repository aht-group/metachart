package org.metachart.util;

import org.metachart.util.OfxChartTypeResolver.AxisOrientation;
import org.metachart.xml.chart.Axis;
import org.metachart.xml.chart.Chart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChartLabelResolver
{	
	final static Logger logger = LoggerFactory.getLogger(ChartLabelResolver.class);
	
	public synchronized static String getTitle(Chart ofxChart)
	{
		String result = null;
		if(ofxChart.isSetTitle()){result = ofxChart.getTitle().getLabel();}
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
				if(axis.isSetCode() && axis.getCode().equals(type.toString()) && axis.isSetLabel() && axis.getLabel().isSetText())
				{
					result = axis.getLabel().getText();
					break;
				}
			}
		}
		return result;
	}
}
