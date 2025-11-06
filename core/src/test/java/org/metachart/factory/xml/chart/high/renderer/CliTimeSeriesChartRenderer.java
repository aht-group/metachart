package org.metachart.factory.xml.chart.high.renderer;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Random;

import org.exlp.util.jx.JaxbUtil;
import org.exlp.util.system.DateUtil;
import org.metachart.factory.pojo.ChartColorFactory;
import org.metachart.factory.xml.chart.high.TimeSeriesChartFactory;
import org.metachart.model.xml.chart.Chart;
import org.metachart.model.xml.chart.Data;
import org.metachart.model.xml.chart.Ds;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CliTimeSeriesChartRenderer
{
	final static Logger logger = LoggerFactory.getLogger(CliTimeSeriesChartRenderer.class);
	
	public CliTimeSeriesChartRenderer()
	{
		
	}
	
	public Chart getTimeSeries()
	{
		TimeSeriesChartFactory cf = new TimeSeriesChartFactory();
//		cf.setWithGaps(true);
		Chart chart = cf.build();
		
		chart.setColors(getColors());
		
//		chart.getDs().add(getX("a"));
//		chart.getDs().add(getX("b"));
		return chart;
	}
	
	private Chart.Colors getColors()
	{
		Chart.Colors colors = new Chart.Colors();
		colors.getColor().add(ChartColorFactory.create(255, 255, 255, 255, ChartColorFactory.Area.backgroundChart));
		
		return colors;
	}
	
	protected Ds getX(String label)
	{
		Random rnd = new Random();
		Ds dataSet = new Ds();
		dataSet.setLabel(label);
		for(int i=1;i<20;i++)
		{
			Data data = new Data();
			data.setRecord(DateUtil.toXmlGc(LocalDate.of(2010, 1, i)));
			data.setY(Integer.valueOf(rnd.nextInt(i)).doubleValue());
			if(rnd.nextInt(100)<70){dataSet.getData().add(data);}
		}
		return dataSet;
	}
	
	public Chart load(String fileName) throws FileNotFoundException
	{
		Chart chart = (Chart)JaxbUtil.loadJAXB(fileName, Chart.class);
		return chart;
	}
	
	public static void main (String[] args) throws Exception
	{
		McBootstrap.init();
			
		CliTimeSeriesChartRenderer test = new CliTimeSeriesChartRenderer();
		Chart chart = test.getTimeSeries();
		
	}
}
