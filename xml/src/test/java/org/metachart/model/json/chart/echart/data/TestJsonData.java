package org.metachart.model.json.chart.echart.data;

import org.exlp.util.io.JsonUtil;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJsonData
{
	final static Logger logger = LoggerFactory.getLogger(TestJsonData.class);
	
    public void build()
    {
    	JsonData json = new JsonData();
    	json.setValue(70d);
    	
    	JsonUtil.info(json);
    }
	
	public static void main(String[] args)
    {
		McBootstrap.init();
		TestJsonData test = new TestJsonData();
		test.build();
    }
}