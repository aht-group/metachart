package org.metachart.chart;

import org.jdom2.Document;
import org.jfree.chart.JFreeChart;
import org.metachart.chart.renderer.BarChartRenderer;
import org.metachart.chart.renderer.TimeBarRenderer;
import org.metachart.chart.renderer.gantt.GanttChartRenderer;
import org.metachart.chart.renderer.ts.TimeSeriesChartRenderer;
import org.metachart.chart.renderer.xy.SplineChartRenderer;
import org.metachart.interfaces.ChartRenderer;
import org.metachart.util.OfxChartTypeResolver;
import org.metachart.xml.chart.Chart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.xml.JDomUtil;

public class OfxChartRenderer
{
	final static Logger logger = LoggerFactory.getLogger(OfxChartRenderer.class);
	
	private ChartRenderer ofxRenderer;
	
	public OfxChartRenderer()
	{
	
	}
	
	public JFreeChart render(Chart ofxChart)
	{
		OfxChartTypeResolver.Type chartType = OfxChartTypeResolver.getType(ofxChart.getRenderer());
		switch(chartType)
		{
			case TimeSeries: ofxRenderer = new TimeSeriesChartRenderer();break;
			case TimeBar:    ofxRenderer = new TimeBarRenderer();break;
			case Bar: 		 ofxRenderer = new BarChartRenderer();break;
			case Gantt:		 ofxRenderer = new GanttChartRenderer();break;
			case Spline:	 ofxRenderer = new SplineChartRenderer();break;
			default:	logger.warn("No Renderer available for "+chartType);
		}
		
		JFreeChart jfreeChart=ofxRenderer.render(ofxChart);
		return jfreeChart;
	}
	
	public JFreeChart render(Document doc)
	{
		Chart ofxChart = (Chart)JDomUtil.toJaxb(doc, Chart.class);
		return render(ofxChart); 
	}
	
	public ChartRenderer getOfxRenderer() {return ofxRenderer;}
}
