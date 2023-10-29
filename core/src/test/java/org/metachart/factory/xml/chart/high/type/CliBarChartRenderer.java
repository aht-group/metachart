package org.metachart.factory.xml.chart.high.type;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.metachart.chart.OfxChartRenderer;
import org.metachart.test.McBootstrap;
import org.metachart.xml.chart.Chart;
import org.metachart.xml.chart.Data;
import org.metachart.xml.chart.Ds;
import org.metachart.xml.chart.Renderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.xml.JaxbUtil;

public class CliBarChartRenderer
{
	final static Logger logger = LoggerFactory.getLogger(CliBarChartRenderer.class);
	
	public CliBarChartRenderer()
	{
		
	}
	
	public Chart getTimeSeries()
	{
		Chart chart = new Chart();
		chart.setLegend(true);
		
		chart.setRenderer(getType());
		
//		chart.getDataSet().add(getX("a"));
//		chart.getContainer().add(getX("b"));
		return chart;
	}
	
	private Renderer getType()
	{
		Renderer type = new Renderer();
		Renderer.Bar tBar = new Renderer.Bar();
		tBar.setVertical(true);
		type.setBar(tBar);
		return type;
	}
	
	protected Ds getX(String label)
	{
		Random rnd = new Random();
		Ds x = new Ds();
		x.setLabel(label);
		for(int i=1;i<20;i++)
		{
			Data data = new Data();
			data.setY(rnd.nextInt(i));
			data.setCategory("cat"+rnd.nextInt(3));
			x.getData().add(data);
		}
		return x;
	}
	
	public static void main (String[] args) throws Exception
	{
		McBootstrap.init();
			
		CliBarChartRenderer test = new CliBarChartRenderer();
		Chart chart = null;
		
		chart = test.getTimeSeries();
		JaxbUtil.debug(chart);
			
		OfxChartRenderer ofxRenderer = new OfxChartRenderer();
		JFreeChart jfreeChart = ofxRenderer.render(chart);
		
		OutputStream os = new FileOutputStream(new File("dist/chart.png"));
		ChartUtilities.writeChartAsPNG(os,jfreeChart,800,300);
	}
}
