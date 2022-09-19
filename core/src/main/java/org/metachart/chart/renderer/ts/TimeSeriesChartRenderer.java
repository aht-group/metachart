package org.metachart.chart.renderer.ts;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.metachart.chart.renderer.generic.XYPlotRenderer;
import org.metachart.exception.McProcessingException;
import org.metachart.interfaces.ChartRenderer;
import org.metachart.processor.ds.DsAccumulatorProcessor;
import org.metachart.processor.ds.date.DsDateAccumulationProcessor;
import org.metachart.processor.ds.date.DsRecordOrdererProcessor;
import org.metachart.processor.timeseries.TimeSeriesGapNullifier;
import org.metachart.util.TimePeriodFactory.OfxChartTimePeriod;
import org.metachart.xml.chart.Chart;
import org.metachart.xml.chart.Data;
import org.metachart.xml.chart.Ds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.DateUtil;

public class TimeSeriesChartRenderer extends XYPlotRenderer implements ChartRenderer
{
	final static Logger logger = LoggerFactory.getLogger(TimeSeriesChartRenderer.class);
	
	public TimeSeriesChartRenderer()
	{
		super();
		logger.debug("Using: "+this.getClass().getSimpleName());
	}
	
	public JFreeChart render(Chart ofxChart)
	{
		this.ofxChart=ofxChart;
		
		setTimePeriod();
		
//        chart = ChartFactory.createTimeSeriesChart(
//        		ChartLabelResolver.getTitle(ofxChart),
//        		null,
//        		null,
//        		createDataset(ofxChart.getDs()),
//        		ofxChart.isLegend(),
//	            true,
//	            false
//	        );
        setColors();
        setGrid();
        setAxis();
        
        return chart;
	}
	
	protected RegularTimePeriod getRtp(Date d)
	{
		RegularTimePeriod rtp;
		switch(ofxTimePeriod)
		{
			case Hour: rtp = new Hour(d);break;
			case Day: rtp = new Day(d);break;
			case Month: rtp = new Month(d);break;
			default: rtp = new Hour(d);break;
		}
		return rtp;
	}
	
	private TimeSeriesCollection createDataset(List<Ds> dataSets) throws McProcessingException
	{
		DsDateAccumulationProcessor dateSummer = DsDateAccumulationProcessor.factory(ofxChart.getRenderer().getRendererTimeseries());
		DsRecordOrdererProcessor recordOrderer = DsRecordOrdererProcessor.factory(ofxChart.getRenderer().getRendererTimeseries());
		DsAccumulatorProcessor cumulator = DsAccumulatorProcessor.factory(ofxChart.getRenderer().getRendererTimeseries());
		
		TimeSeriesGapNullifier gapNuller=null;
		boolean nullifyGaps = TimeSeriesGapNullifier.gapNullerActivated(ofxChart.getRenderer().getRendererTimeseries());
		if(nullifyGaps)
		{
			OfxChartTimePeriod timePeriod = OfxChartTimePeriod.valueOf(ofxChart.getRenderer().getRendererTimeseries().getTimePeriod());
			gapNuller = new TimeSeriesGapNullifier(timePeriod);
		}
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		for(Ds dataSet : dataSets)
		{
			dataSet = recordOrderer.process(dataSet);
			dataSet = dateSummer.process(dataSet);
			dataSet = cumulator.process(dataSet);
			
			if(nullifyGaps){dataSet = gapNuller.nullifyGapsInContainer(dataSet);}
			TimeSeries ts = new TimeSeries(dataSet.getLabel());
			
			for(Data data : dataSet.getData())
			{
				Date d = DateUtil.toDate(LocalDate.of(data.getRecord().getYear(), data.getRecord().getMonth(), data.getRecord().getDay()));
				RegularTimePeriod rtp = getRtp(d);
				if(data.isSetY())
				{
					ts.addOrUpdate(rtp, data.getY());
				}
				else
				{
					ts.addOrUpdate(rtp, null);
				}
			}
			dataset.addSeries(ts);
			
			logger.info("Add here TS with same color from container");
		}
		return dataset;
	}
}