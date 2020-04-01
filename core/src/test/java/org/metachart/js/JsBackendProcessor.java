package org.metachart.js;

import java.io.FileNotFoundException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JsBackendProcessor 
{
	private ScriptEngine engine;
	
	public JsBackendProcessor() 
	{
	    engine = new ScriptEngineManager().getEngineByName("javascript");
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
	
	public static void main(String args[]) throws ScriptException, FileNotFoundException 
	{
		JsBackendProcessor cli = new JsBackendProcessor();
		cli.executeJs();
	}
}

