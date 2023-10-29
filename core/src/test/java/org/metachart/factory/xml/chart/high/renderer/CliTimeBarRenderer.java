package org.metachart.factory.xml.chart.high.renderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.metachart.chart.OfxChartRenderer;
import org.metachart.test.McBootstrap;
import org.metachart.xml.chart.Chart;
import org.metachart.xml.chart.Grid;
import org.metachart.xml.chart.Renderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.xml.JaxbUtil;

public class CliTimeBarRenderer
{
	final static Logger logger = LoggerFactory.getLogger(CliTimeBarRenderer.class);
	
	public CliTimeBarRenderer()
	{
		
	}
	
	public Chart getTimeSeries()
	{
		Chart chart = new Chart();
		chart.setLegend(true);
		
		chart.setRenderer(getType());
		chart.setGrid(getGrid());
		
//		chart.getDs().add(getX("a"));
		return chart;
	}
	
	private Grid getGrid()
	{
		Grid grid = new Grid();
		grid.setDomain(false);
		grid.setRange(false);
		return grid;
	}
	
	private Renderer getType()
	{
		Renderer type = new Renderer();
		Renderer.Timebar tBar = new Renderer.Timebar();
		tBar.setShadow(false);
		tBar.setGradient(false);
		type.setTimebar(tBar);
		return type;
	}
	
	public static void main (String[] args) throws Exception
	{		
		McBootstrap.init();
		
		CliTimeBarRenderer test = new CliTimeBarRenderer();
		Chart chart = test.getTimeSeries();
		JaxbUtil.info(chart);
			
		OfxChartRenderer ofxRenderer = new OfxChartRenderer();
		JFreeChart jfreeChart = ofxRenderer.render(chart);
		
		OutputStream os = new FileOutputStream(new File("target/chart.png"));
		ChartUtilities.writeChartAsPNG(os,jfreeChart,800,300);
	}
}
