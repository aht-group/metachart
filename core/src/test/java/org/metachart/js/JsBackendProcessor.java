package org.metachart.js;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.metachart.graph.TestGraph2DotFactory;
import org.metachart.test.McCoreTestBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * run against docker run -d -p 51889:8080 lpeters999/highcharts-export-npm:latest
 */
public class JsBackendProcessor 
{	
	final static Logger logger = LoggerFactory.getLogger(JsBackendProcessor.class);
	
	private HttpClient client;
	private ScriptEngine engine;
	
	private static String chartJson = "{\"infile\": {\"chart\": {\"type\": \"column\"}, \"title\": {\"text\": \"Stacked column chart\"},"
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
			+ "]},"
			+ " \"type\": \"svg\","
			+ " \"resources\": {\"files\": \"highcharts.js,modules/exporting.js,modules/export-data.js\"}}";
	
	public JsBackendProcessor() 
	{
		client = HttpClientBuilder.create().build();
//	    engine = new ScriptEngineManager().getEngineByName("javascript");
	}
	
	private static String[] xPut(String json) 
	{
//		curl -H "Content-Type: application/json" -X POST -d '{"infile": {"chart": {"type": "column"}, "title": {"text": "Stacked column chart"}, "xAxis": {"categories": ["Apples", "Oranges", "Pears", "Grapes", "Bananas"]}, "yAxis": {"min": 0, "title": {"text": "Total fruit consumption"}, "stackLabels": {"enabled": true, "style": {"fontWeight": "bold"}}}, "legend": {"align": "right", "x": -30, "verticalAlign": "top", "y": 25, "floating": true, "borderColor": "#CCC", "borderWidth": 1, "shadow": false}, "plotOptions": {"column": {"stacking": "normal", "dataLabels": {"enabled": true}}}, "series": [{"name": "John", "data": [5, 3, 4, 7, 2]}, {"name": "Jane", "data": [2, 2, 3, 2, 1]}, {"name": "Joe", "data": [3, 4, 4, 2, 5]}]}, "type": "svg", "resources": {"files": "highcharts.js,modules/exporting.js,modules/export-data.js"}}' http://export.highcharts.com -o /tmp/highcharts_testchart.svg
//		http://export.highcharts.com -o /tmp/highcharts_testchart.svg
			
		String[] stringGet = {"curl", "-X", "POST", "192.168.1.20:8999", "-H", "accept: application/json", "-H", "content-type: application/json", "-d", json };
		Stream.of(stringGet).forEach(s -> System.out.println("TEST: " + s ));
		return stringGet;
	}
	
	private static String runProcess(String[] strings) throws IOException, InterruptedException 
	{
		ProcessBuilder ps = new ProcessBuilder(strings);
		Process pr = ps.start();
		pr.waitFor();

		BufferedReader reader2 = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line2 = "";	
		
		while ((line2 = reader2.readLine()) != null) {sb.append(line2 + "\n");}
		
		return sb.toString();
	}
	
	public void executeJs() throws ScriptException 
	{
		/*Load single module:*/
//		engine.eval("var FULL_PATH = '../jsf/src/main/resources/META-INF/resources/jsMetaChart/highchart-8.0.4/';");
//		engine.eval("load(FULL_PATH + 'code/highcharts.js');");
		
		/*Load multiple modules:*/
		engine.eval("var userAgent = '';");
		engine.eval("var FULL_PATH = '../jsf/src/main/resources/META-INF/resources/jsMetaChart/', "
				+ "s = [FULL_PATH + 'highchart-8.0.4/code/highcharts.js', "
				+ "FULL_PATH + 'highchart-8.0.4/code/highcharts-more.js', "
				+ "FULL_PATH + 'highchart-8.0.4/code/modules/exporting.js', "
				+ "FULL_PATH + 'highchart-8.0.4/code/modules/export-data.js', "
				+ "FULL_PATH + 'highchart-8.0.4/code/modules/accessibility.js', "
				+ "];");				
		engine.eval("if (s.length > 0){for (i = 0; i < s.length; i++){load(s[i]);}}");
	}
	
	public String httpPost(String url, String json) throws ClientProtocolException, IOException
	{
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("accept", "application/json");
		StringEntity stringEntity = new StringEntity(json);
		httpPost.setEntity(stringEntity);
		HttpResponse httpRespnse = client.execute(httpPost);
		return EntityUtils.toString(httpRespnse.getEntity(),"UTF-8");
	}
	
	public static void main(String args[]) throws ScriptException, ClientProtocolException, IOException 
	{
		McCoreTestBootstrap.init();
		JsBackendProcessor cli = new JsBackendProcessor();
		try {String response = runProcess(xPut(chartJson));logger.info(response);} catch (IOException | InterruptedException e) {e.printStackTrace();};
		logger.info(cli.httpPost("http://192.168.1.20:8999/", chartJson));
//		cli.executeJs();
	}
}