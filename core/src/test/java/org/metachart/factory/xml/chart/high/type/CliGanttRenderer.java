package org.metachart.factory.xml.chart.high.type;

import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.metachart.chart.OfxChartRenderer;
import org.metachart.factory.pojo.ChartColorFactory;
import org.metachart.model.xml.chart.Chart;
import org.metachart.model.xml.chart.Color;
import org.metachart.model.xml.chart.Data;
import org.metachart.model.xml.chart.Ds;
import org.metachart.model.xml.chart.Grid;
import org.metachart.model.xml.chart.Renderer;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.DateUtil;
import net.sf.exlp.util.xml.JaxbUtil;

public class CliGanttRenderer
{
	final static Logger logger = LoggerFactory.getLogger(CliGanttRenderer.class);
	
	public CliGanttRenderer()
	{
		
	}
	
	public Chart getGantt()
	{
		Chart chart = new Chart();
		chart.setLegend(true);
		chart.setColors(getColors());
		chart.setRenderer(getType());
		chart.setGrid(getGrid());
		
//		chart.getDs().add(getX("Person A"));
		return chart;
	}
	
	private Grid getGrid()
	{
		Grid grid = new Grid();
		grid.setDomain(false);
		grid.setRange(false);
		return grid;
	}
	
	private Chart.Colors getColors()
	{
		Chart.Colors colors = new Chart.Colors();
		
		Color c1 = ChartColorFactory.create(200,200,200,1);
		c1.setTyp("task");c1.setCode("na");
		colors.getColor().add(c1);
		
		Color c2 = ChartColorFactory.create(100,200,50,1);
		c2.setTyp("task");c2.setCode("holiday");
		colors.getColor().add(c2);
		
		return colors;
	}
	
	private Renderer getType()
	{
		Renderer type = new Renderer();
		Renderer.Gantt tGantt = new Renderer.Gantt();
		type.setGantt(tGantt);
		return type;
	}
	
	protected Ds getX(String label)
	{
		Ds c = new Ds();
		c.setLabel(label);
		
		Data d1 = new Data();
		d1.setFrom(DateUtil.toXmlGc(LocalDate.of(2010,1,1)));
		d1.setTo(DateUtil.toXmlGc(LocalDate.of(2010,1,10)));
		d1.setCategory("na");
		c.getData().add(d1);
		
		return c;
	}
	
	public static void main (String[] args) throws Exception
	{
		McBootstrap.init();
			
		Chart chart;
		
//		TestGanttRenderer test = new TestGanttRenderer();
//		chart = test.getGantt();
		chart = (Chart)JaxbUtil.loadJAXB(args[0],Chart.class);
		
		logger.info("Using Chart from "+args[0]);
		
		JaxbUtil.debug(chart);
			
		OfxChartRenderer ofxRenderer = new OfxChartRenderer();
		JFreeChart jfreeChart = ofxRenderer.render(chart);
		
		Dimension d = ofxRenderer.getOfxRenderer().getSuggestedSize();
		logger.debug(d.toString());
		OutputStream os = new FileOutputStream(new File("dist/chart.png"));
		ChartUtilities.writeChartAsPNG(os,jfreeChart,800,d.height);
	}
}
