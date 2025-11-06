package org.metachart.factory.xml.chart.high.type;

import java.util.Random;

import org.exlp.util.jx.JaxbUtil;
import org.metachart.model.xml.chart.Chart;
import org.metachart.model.xml.chart.Data;
import org.metachart.model.xml.chart.Ds;
import org.metachart.model.xml.chart.Renderer;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
			data.setY(Integer.valueOf(rnd.nextInt(i)).doubleValue());
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
	}
}
