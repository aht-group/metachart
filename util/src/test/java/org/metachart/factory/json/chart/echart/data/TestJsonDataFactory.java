package org.metachart.factory.json.chart.echart.data;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

import org.exlp.util.io.JsonUtil;
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
	
	public void time() throws IOException
	{
		JsonDataFactory jf = JsonDataFactory.instance();
		jf.time(LocalDateTime.now(), 10);
		JsonData data1 = jf.assemble();
		JsonUtil.info(data1);
		
		
		
		Path pJson = McBootstrap.pTemp.resolve("data.json");
		JsonUtil.instance().write(data1,pJson);
		logger.info("saved to "+pJson);
		
		JsonData data2 = JsonUtil.read(JsonData.class,pJson.toFile());
		JsonUtil.info(data2);
		logger.info("Read "+pJson);
		
		JsonData data3 = JsonUtil.instance().read(JsonData.class,pJson);
		JsonUtil.info(data3);
	}
	
	public static void main(String args[]) throws IOException
	{
		McBootstrap.init();
		TestJsonDataFactory cli = new TestJsonDataFactory();
		
//		cli.randomDouble1();
		cli.time();
	}
}