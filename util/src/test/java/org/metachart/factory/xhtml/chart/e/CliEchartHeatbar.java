package org.metachart.factory.xhtml.chart.e;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;

import org.exlp.interfaces.system.property.Configuration;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoHeatbar;
import org.metachart.model.json.chart.echart.JsonEchart;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CliEchartHeatbar extends AbstractCliEchart
{
	final static Logger logger = LoggerFactory.getLogger(CliEchartHeatbar.class);

	public CliEchartHeatbar(Configuration config)
	{
		type = JsonEchart.Type.heatbar;
	}
	
	public void demo() throws IOException
	{
		StringWriter sw = new StringWriter();
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(sw,JsonUtil.instance()).declare(xfEchart.getDivId(),JsonHtmlFactory.build(JsonHtmlFactory.Renderer.canvas,false));
		EchartDemoHeatbar.instance(jfEchart).demo();
		jfEchart.init();
		super.render(true,sw,"demo");
	}
	
	public static void main (String[] args) throws Exception
	{
		Configuration config = McBootstrap.init();
		CliEchartHeatbar cli = new CliEchartHeatbar(config);

		JsonDataFactory jfAxis = JsonDataFactory.instance();
		jfAxis.axisRange(LocalDateTime.now(), LocalDateTime.now());
		jfAxis.axisRange(LocalDateTime.now(), LocalDateTime.now());
		
		cli.demo();
	}
}