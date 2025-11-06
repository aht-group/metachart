package org.metachart.factory.xml.chart.high.renderer;

import org.exlp.util.jx.JaxbUtil;
import org.metachart.model.xml.chart.Chart;
import org.metachart.model.xml.chart.Grid;
import org.metachart.model.xml.chart.Renderer;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
			
	}
}
