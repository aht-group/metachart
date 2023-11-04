package org.metachart.factory.json.chart.echart.data;

import org.metachart.factory.txt.chart.TxtDataFactory;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJsonDataFactory
{	
	final static Logger logger = LoggerFactory.getLogger(McBootstrap.class);
	
	public void randomDouble1()
	{
		JsonData data = JsonDataFactory.random1Int(10, 5);
		logger.info(TxtDataFactory.double1ToInteger1(data));
	}
	
	
	public static void main(String args[])
	{
		McBootstrap.init();
		TestJsonDataFactory cli = new TestJsonDataFactory();
		
		cli.randomDouble1();
	}
}