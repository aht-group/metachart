package org.metachart.chart.renderer;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.exlp.util.system.DateUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.ClusteredXYBarRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.metachart.chart.renderer.generic.XYPlotRenderer;
import org.metachart.factory.chart.high.ChartLabelResolver;
import org.metachart.interfaces.ChartRenderer;
import org.metachart.model.xml.chart.Chart;
import org.metachart.model.xml.chart.Data;
import org.metachart.model.xml.chart.Ds;
import org.metachart.model.xml.chart.Renderer.Timebar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeBarRenderer extends XYPlotRenderer implements ChartRenderer
{
	final static Logger logger = LoggerFactory.getLogger(TimeBarRenderer.class);
	
	public TimeBarRenderer()
	{
		logger.debug("Active");
	}
	
	public JFreeChart render(Chart ofxChart)
	{
		this.ofxChart=ofxChart;
		Timebar timebar = ofxChart.getRenderer().getTimebar();
        chart = ChartFactory.createXYBarChart(
        	ChartLabelResolver.getTitle(ofxChart),
        	ChartLabelResolver.getAxisLabelX(ofxChart),
            true,
            ChartLabelResolver.getAxisLabelY(ofxChart),                        // range axis label
            createDataset(ofxChart.getDs().getDs()),                    // data
            PlotOrientation.VERTICAL,
            ofxChart.isLegend(),                       // include legend
            true,
            false
        );

        
        setColors();
        setGrid();
        
        XYPlot plot = (XYPlot) chart.getPlot();
        ClusteredXYBarRenderer renderer = new ClusteredXYBarRenderer(0.0, false);
        
        if(Objects.nonNull(timebar.isShadow())) {renderer.setShadowVisible(timebar.isShadow());}
        if(Objects.nonNull(timebar.isGradient()) && timebar.isGradient())
        {
        	renderer.setBarPainter(new StandardXYBarPainter());
        }
        renderer.setDrawBarOutline(false);
        
        plot.setRenderer(renderer);
        return chart;
	}

	private IntervalXYDataset createDataset(List<Ds> lContainer)
	{
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		for(Ds container : lContainer)
		{
			TimeSeries ts = new TimeSeries(container.getLabel());
			for(Data data : container.getData())
			{
					Date d = DateUtil.toDate(LocalDate.of(data.getRecord().getYear(), data.getRecord().getMonth(), data.getRecord().getDay()));
					ts.addOrUpdate(new Hour(d), data.getY());
			}
			 dataset.addSeries(ts);
		}
		return dataset;
	}
}
