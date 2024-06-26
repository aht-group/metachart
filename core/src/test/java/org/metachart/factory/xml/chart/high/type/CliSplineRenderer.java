package org.metachart.factory.xml.chart.high.type;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Random;

import org.exlp.util.jx.JaxbUtil;
import org.exlp.util.system.DateUtil;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.metachart.chart.OfxChartRenderer;
import org.metachart.factory.chart.high.TimePeriodFactory;
import org.metachart.factory.pojo.ChartColorFactory;
import org.metachart.model.xml.chart.Chart;
import org.metachart.model.xml.chart.Data;
import org.metachart.model.xml.chart.Ds;
import org.metachart.model.xml.chart.Renderer;
import org.metachart.model.xml.chart.RendererTimeseries;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CliSplineRenderer
{
	final static Logger logger = LoggerFactory.getLogger(CliSplineRenderer.class);
	
	public CliSplineRenderer()
	{
		
	}
	
	public Chart getTimeSeries()
	{
		Chart chart = new Chart();
		chart.setLegend(true);
		
		chart.setRenderer(getType());
		chart.setColors(getColors());
		
//		chart.getDataSet().add(getX("a"));
//		chart.getContainer().add(getX("b"));
		return chart;
	}
	
	private Renderer getType()
	{
		Renderer type = new Renderer();
		RendererTimeseries tsType = new RendererTimeseries();
		tsType.setGap(true);
		tsType.setTimePeriod(TimePeriodFactory.OfxChartTimePeriod.Day.toString());
		type.setRendererTimeseries(tsType);
		return type;
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
		Ds x = new Ds();
		x.setLabel(label);
		for(int i=1;i<20;i++)
		{
			Data data = new Data();
			data.setRecord(DateUtil.toXmlGc(LocalDate.of(2010, 1, i)));
			data.setY(Integer.valueOf(rnd.nextInt(i)).doubleValue());
			if(rnd.nextInt(100)<70){x.getData().add(data);}
		}
		return x;
	}
	
	public Chart load(String fileName) throws FileNotFoundException
	{
		Chart chart = (Chart)JaxbUtil.loadJAXB(fileName, Chart.class);
		return chart;
	}
	
	public static void main (String[] args) throws Exception
	{
		McBootstrap.init();
		
		CliSplineRenderer test = new CliSplineRenderer();
		Chart chart;
		chart = test.load(args[0]);
		
//		JaxbUtil.debug(chart, new OfxNsPrefixMapper());
			
		OfxChartRenderer ofxRenderer = new OfxChartRenderer();
		JFreeChart jfreeChart = ofxRenderer.render(chart);
		
		OutputStream os = new FileOutputStream(new File("dist/chart.png"));
		ChartUtilities.writeChartAsPNG(os,jfreeChart,800,300);
	}
}
