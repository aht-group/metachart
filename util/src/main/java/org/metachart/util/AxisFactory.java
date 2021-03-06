package org.metachart.util;

import java.awt.Font;
import java.text.SimpleDateFormat;

import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.PeriodAxis;
import org.jfree.chart.axis.PeriodAxisLabelInfo;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Month;
import org.jfree.data.time.Year;
import org.metachart.util.OfxChartTypeResolver.AxisOrientation;
import org.metachart.util.TimePeriodFactory.OfxChartTimePeriod;
import org.metachart.xml.chart.AxisType;
import org.metachart.xml.chart.Chart;
import org.metachart.xml.chart.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AxisFactory
{
	final static Logger logger = LoggerFactory.getLogger(AxisFactory.class);
	
	public static synchronized Axis createNumberAxis(Chart ofxChart, AxisOrientation type)
	{
		org.metachart.xml.chart.Axis ofxAxis = AxisFactory.getAxis(ofxChart, type);
		
		Axis axis = null;
		switch(OfxChartTypeResolver.getAxisType(ofxAxis.getAxisType()))
		{
			case Number: axis = createNumberAxis(ofxAxis);break;
			default: logger.warn("NYI");
		}
		
		
		return axis;
	}
	
	public static synchronized NumberAxis createNumberAxis(org.metachart.xml.chart.Axis ofxAxis)
	{
		AxisType.Number ofxNumberAxis = ofxAxis.getAxisType().getNumber();
		NumberAxis axis = new NumberAxis();
		boolean autoRangeIncludesZero = true;
		if(ofxAxis.isSetAutoRangIncludeNull()){autoRangeIncludesZero = ofxAxis.isAutoRangIncludeNull();}
		axis.setAutoRangeIncludesZero(autoRangeIncludesZero);
		
		logger.debug("ticker: "+ofxNumberAxis.isSetTicker());
		if(ofxNumberAxis.isSetTicker() && ofxNumberAxis.getTicker().isSetSize())
		{
			logger.debug("size: "+ofxNumberAxis.getTicker().getSize());
			axis.setTickUnit(new NumberTickUnit(ofxNumberAxis.getTicker().getSize()));
		}
		labelAxisAxis(axis, ofxAxis);
		return axis;
	}
	
	public static synchronized PeriodAxis createPeriodAxis(org.metachart.xml.chart.Axis ofxAxis)
	{
		AxisType.Date ofxDateAxis = ofxAxis.getAxisType().getDate();
		int level = ofxDateAxis.getTicker().size();
		logger.debug("Level: "+level);
		
		PeriodAxis axis = new PeriodAxis(null);
		axis.setAutoRangeTimePeriodClass(Month.class);
		axis.setMajorTickTimePeriodClass(Month.class);
		
		if(ofxDateAxis.isSetAutoRangeTimePeriod())
		{
			axis.setAutoRangeTimePeriodClass(TimePeriodFactory.getPeriodClass(ofxDateAxis.getAutoRangeTimePeriod()));
		}
		if(ofxDateAxis.isSetMajorTickTimePeriod())
		{
			axis.setMajorTickTimePeriodClass(TimePeriodFactory.getPeriodClass(ofxDateAxis.getMajorTickTimePeriod()));
		}
		
		PeriodAxisLabelInfo[] info = new PeriodAxisLabelInfo[level];
		int i=0;
		for(org.metachart.xml.chart.AxisType.Date.Ticker dt : ofxAxis.getAxisType().getDate().getTicker())
		{
			SimpleDateFormat sdf = new SimpleDateFormat(dt.getFormat());
			OfxChartTimePeriod ofxTp = OfxChartTimePeriod.valueOf(dt.getTimePeriod());
			switch(ofxTp)
			{
				case Hour:  info[i] = new PeriodAxisLabelInfo(Hour.class,sdf);break;
				case Day:   info[i] = new PeriodAxisLabelInfo(Day.class,sdf);break;
				case Month: info[i] = new PeriodAxisLabelInfo(Month.class,sdf);break;
				case Year:  info[i] = new PeriodAxisLabelInfo(Year.class,sdf);break;
			}
			i++;
		}
		axis.setLabelInfo(info);
		
		labelAxisAxis(axis, ofxAxis);
		return axis;
	}
	
	public static synchronized void labelAxisAxis(Axis axis, org.metachart.xml.chart.Axis ofxAxis)
	{
		if(ofxAxis.isSetLabel())
		{
			Label ofxLabel = ofxAxis.getLabel();
			if(ofxLabel.isSetText())
			{
				axis.setLabel(ofxLabel.getText());
				if(ofxLabel.isSetSize() || ofxLabel.isSetFont())
				{
					axis.setLabelFont(createFont(ofxLabel));
				}
			}
		}
	}
	
	private static synchronized Font createFont(Label ofxLabel)
	{
		String fontFamily = "SansSerif";
		int fontSize=10;
		if(ofxLabel.isSetFont()){fontFamily = ofxLabel.getFont();}
		if(ofxLabel.isSetSize()){fontSize=ofxLabel.getSize();}
		
		Font font = new Font(fontFamily, Font.PLAIN, fontSize);
		return font;
	}
	
	private static synchronized org.metachart.xml.chart.Axis getAxis(Chart ofxChart, AxisOrientation type)
	{
		org.metachart.xml.chart.Axis axisResult = null;
		for(org.metachart.xml.chart.Axis axis : ofxChart.getAxis())
		{
			if(axis.isSetCode() && axis.getCode().equals(type.toString())){axisResult = axis;}
		}
		return axisResult;
	}
}
