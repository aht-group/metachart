package org.metachart.js;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.script.ScriptException;

public class JsBackendProcessor 
{
	
/* Working export-server runs with:
 * 
 * docker run -d --name highcharts -p 8889:8080 onsdigital/highcharts-export-node 
 * 
 * 
 * */
	
//	private ScriptEngine engine;
	
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
//	    engine = new ScriptEngineManager().getEngineByName("javascript");
	}
	
	private static String[] xPut(String json) 
	{
	
//		curl -H "Content-Type: application/json" -X POST -d '{"infile": {"chart": {"type": "column"}, "title": {"text": "Stacked column chart"}, "xAxis": {"categories": ["Apples", "Oranges", "Pears", "Grapes", "Bananas"]}, "yAxis": {"min": 0, "title": {"text": "Total fruit consumption"}, "stackLabels": {"enabled": true, "style": {"fontWeight": "bold"}}}, "legend": {"align": "right", "x": -30, "verticalAlign": "top", "y": 25, "floating": true, "borderColor": "#CCC", "borderWidth": 1, "shadow": false}, "plotOptions": {"column": {"stacking": "normal", "dataLabels": {"enabled": true}}}, "series": [{"name": "John", "data": [5, 3, 4, 7, 2]}, {"name": "Jane", "data": [2, 2, 3, 2, 1]}, {"name": "Joe", "data": [3, 4, 4, 2, 5]}]}, "type": "svg", "resources": {"files": "highcharts.js,modules/exporting.js,modules/export-data.js"}}' http://export.highcharts.com -o /tmp/highcharts_testchart.svg
	
//		http://export.highcharts.com -o /tmp/highcharts_testchart.svg
			
		String[] stringGet = {"curl", "-X", "POST", "http://0.0.0.0:8889", "-H", "accept: application/json", "-H", "content-type: application/json", "-d", json };
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
	
//	public void executeJs() throws ScriptException 
//	{
//		/*Load single module:*/
////		engine.eval("var FULL_PATH = '../jsf/src/main/resources/META-INF/resources/jsMetaChart/highchart-8.0.4/';");
////		engine.eval("load(FULL_PATH + 'code/highcharts.js');");
//		
//		/*Load multiple modules:*/
//		engine.eval("var userAgent = '';");
//		engine.eval("var FULL_PATH = '../jsf/src/main/resources/META-INF/resources/jsMetaChart/', "
//				+ "s = [FULL_PATH + 'highchart-8.0.4/code/highcharts.js', "
//				+ "FULL_PATH + 'highchart-8.0.4/code/highcharts-more.js', "
//				+ "FULL_PATH + 'highchart-8.0.4/code/modules/exporting.js', "
//				+ "FULL_PATH + 'highchart-8.0.4/code/modules/export-data.js', "
//				+ "FULL_PATH + 'highchart-8.0.4/code/modules/accessibility.js', "
//				+ "];");
//				
//		engine.eval("if (s.length > 0){for (i = 0; i < s.length; i++){load(s[i]);}}");
//
//	}
	
	public static void main(String args[]) throws ScriptException, FileNotFoundException 
	{
		JsBackendProcessor cli = new JsBackendProcessor();
		try {
			String response = runProcess(xPut(chartJson));
			Files.write(Paths.get("/home/us3r/test.svg"), response.getBytes());
			System.out.println(response);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	cli.executeJs();
	}
}

