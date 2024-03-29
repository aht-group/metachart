package org.metachart.chart.renderer;

import java.awt.Dimension;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.metachart.chart.renderer.generic.AbstractChartRenderer;
import org.metachart.interfaces.ChartRenderer;
import org.metachart.model.xml.chart.Chart;
import org.metachart.model.xml.chart.Data;
import org.metachart.model.xml.chart.Ds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BarChartRenderer extends AbstractChartRenderer implements ChartRenderer
{
	final static Logger logger = LoggerFactory.getLogger(BarChartRenderer.class);
	
	public BarChartRenderer()
	{
		logger.debug("Active");
	}
	
	public JFreeChart render(Chart ofxChart)
	{
        JFreeChart chart = null;
        logger.warn("NYI");
//        ChartFactory.createBarChart(
//        		ChartLabelResolver.getTitle(ofxChart),
//        		ChartLabelResolver.getAxisLabelX(ofxChart),
//        		ChartLabelResolver.getAxisLabelY(ofxChart),
//        		createDataset(ofxChart.getDataSet()),
//        		PlotOrientation.VERTICAL,
//        		ofxChart.isLegend(),
//	            true,               // generate tooltips?
//	            false               // generate URLs?
//	        );
        return chart;
	}
	
	private CategoryDataset createDataset(List<Ds> lContainer)
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(Ds container : lContainer)
		{
			for(Data data : container.getData())
			{
				dataset.addValue(data.getY(), container.getLabel(), data.getCategory());
			}	
		}
        return dataset;
    }
	
	public Dimension getSuggestedSize()
	{
		logger.error("This should be @Overridden");
		throw new UnsupportedOperationException();
	}
}
