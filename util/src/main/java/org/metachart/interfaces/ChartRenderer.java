package org.metachart.interfaces;

import java.awt.Dimension;

import org.jfree.chart.JFreeChart;
import org.metachart.model.xml.chart.Chart;

public interface ChartRenderer
{
	JFreeChart render(Chart ofxChart);
	Dimension getSuggestedSize();
}
