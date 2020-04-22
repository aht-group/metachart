package org.metachart.js;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.script.ScriptException;

import org.apache.commons.io.FilenameUtils;

public class JsBackendProcessor 
{
	
/* Working export-server runs with:
 * 
 * docker run -d --name highcharts -p 8889:8080 onsdigital/highcharts-export-node 
 * 
 * 
 * */
	
//	private ScriptEngine engine;
	
	private static String timeJson = "{ infile : { 'container', { "
			+ "title: { text: 'Solar Employment Growth by Sector, 2010-2016' }, "
			+ "subtitle: { text: 'Source: thesolarfoundation.com' }, "
			+ "yAxis: { title: { text: 'Number of Employees' } }, "
			+ "xAxis: { accessibility: { rangeDescription: 'Range: 2010 to 2017' } }, "
			+ "legend: { layout: 'vertical', align: 'right', verticalAlign: 'middle' }, "
			+ "plotOptions: { "
			+ "series: { label: { connectorAllowed: false }, pointStart: 2010 } }, "
			+ "series: [{ "
			+ "name: 'Installation', data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175] }, { "
			+ "name: 'Manufacturing', data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434] }, { "
			+ "name: 'Sales & Distribution', data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387] }, { "
			+ "name: 'Project Development', data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227] }, { "
			+ "name: 'Other', data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111] }], "
			+ "responsive: { rules: [{ "
			+ "condition: { maxWidth: 500 }, "
			+ "chartOptions: { legend: { layout: 'horizontal', align: 'center', verticalAlign: 'bottom' } } }] } }), "
			+ "type: svg, "
			+ "resources: {files: highcharts.js,modules/exporting.js,modules/export-data.js}}";
	
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
			
//		String[] stringGet = {"sh", "-c", "curl", "-X", "POST", "http://localhost:8889", "-H", "accept: application/json", "-H", "content-type: application/json", "-d", json };
		String[] stringGet = {"sh", "-c", "curl -X POST http://localhost:8889 -H accept: application/json -H content-type: application/json -d", json};
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
			String response = runProcess(xPut(timeJson));
			Files.write(Paths.get("C:\\DOCKER\\test.svg"), response.getBytes());
			System.out.println(response);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	cli.executeJs();
	}
}

