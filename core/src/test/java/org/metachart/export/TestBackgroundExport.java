package org.metachart.export;

import java.io.IOException;

import javax.script.ScriptException;

import org.apache.http.client.ClientProtocolException;
import org.metachart.processor.export.BackgroundExportProcessor;
import org.metachart.test.McCoreTestBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * run against docker run -d -p 51889:8080 lpeters999/highcharts-export-npm:latest
 */
public class TestBackgroundExport 
{	
	final static Logger logger = LoggerFactory.getLogger(TestBackgroundExport.class);
	
	private static String chartJson = "{\"chart\": {\"type\": \"column\"}, \"title\": {\"text\": \"Stacked column chart\"},"
			+ " \"xAxis\": {\"categories\": [\"Apples\", \"Oranges\", \"Pears\", \"Grapes\", \"Bananas\"]},"
			+ " \"yAxis\": {\"min\": 0,"
			+ " \"title\": {\"text\": \"Total fruit consumption\"},"
			+ " \"stackLabels\": {\"enabled\": true,"
			+ " \"style\": {\"fontWeight\": \"bold\"}}},"
			+ " \"legend\": {\"align\": \"right\", \"x\": -30, \"verticalAlign\": \"top\", \"y\": 25, \"floating\": true,"
			+ " \"borderColor\": \"#CCC\","
			+ " \"borderWidth\": 1, \"shadow\": false},"
			+ " \"plotOptions\": {\"column\": {\"stacking\": \"normal\", \"dataLabels\": {\"enabled\": true}}},"
			+ " \"series\":["
			+ "{\"name\": \"John\", \"data\": [5, 3, 4, 7, 2]},"
			+ " {\"name\": \"Jane\", \"data\": [2, 2, 3, 2, 1]},"
			+ " {\"name\": \"Joe\", \"data\": [3, 4, 4, 2, 5]}"
			+ "]}";
		
	public TestBackgroundExport()
	{
		logger.info("Using: "+chartJson);
	}

	public static void main(String args[]) throws ScriptException, ClientProtocolException, IOException 
	{
		McCoreTestBootstrap.init();
		BackgroundExportProcessor cli = new BackgroundExportProcessor();
//		logger.info(cli.exportSVG("http://192.168.1.20:8999/", chartJson, "png"));
//		logger.info(cli.exportSVG("http://192.168.1.20:8999/", chartJson, "svg"));
	}
}