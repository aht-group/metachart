package org.metachart.factory.xhtml.chart.e;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import org.exlp.interfaces.system.property.Configuration;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonDatasFactory;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoTime;
import org.metachart.factory.json.chart.echart.js.family.JsonEchartTimeFactory;
import org.metachart.model.json.chart.echart.JsonEchart;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CliEchartTime extends AbstractCliEchart
{
	final static Logger logger = LoggerFactory.getLogger(CliEchartTime.class);

	public CliEchartTime(Configuration config)
	{
		type = JsonEchart.Type.time;
		logger.info("Wrting to "+McBootstrap.pTemp.toString());
	}
	
	public void demo() throws IOException
	{
		StringWriter sw = new StringWriter();
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(sw,JsonUtil.instance()).declare(xfEchart.getDivId(),JsonHtmlFactory.build(JsonHtmlFactory.Renderer.canvas,false));
		EchartDemoTime.demo(jfEchart);
		jfEchart.init();
		this.render(false,sw,McBootstrap.pTemp.resolve("echart-"+type.toString()+".demo.html"));
	}
	
	public void jsf() throws IOException
	{
		JsonDatas datas = EchartDemoTime.toDatas();
		JsonUtil.instance().write(datas, McBootstrap.pTemp.resolve("echart-"+type.toString()+".datas.json"));
		
		StringWriter sw = new StringWriter();
		JsonEchartTimeFactory f = JsonEchartTimeFactory.instance(sw).id(xfEchart.getDivId()); 
		f.json(null,datas,EchartDemoTime.toOption());
		super.render(false,sw,McBootstrap.pTemp.resolve("echart-"+type.toString()+".jsf.html"));
	}
	
	public void app() throws IOException
	{
		Path p = McBootstrap.pTemp.resolve("echart-"+JsonEchart.Type.time+".chart.json");
		if(Files.exists(p))
		{
			JsonEchart chart = JsonUtil.instance().read(JsonEchart.class,p);
			
			StringWriter sw = new StringWriter();
			JsonEchartTimeFactory f = JsonEchartTimeFactory.instance(sw).id(xfEchart.getDivId()); 
			f.json(null, JsonDatasFactory.build(chart.getDatas()),chart.getOption());
			this.render(false,sw,McBootstrap.pTemp.resolve("echart-"+type.toString()+".app.html"));
		}
	}

	public static void main (String[] args) throws Exception
	{
		Configuration config = McBootstrap.init();
		CliEchartTime cli = new CliEchartTime(config);

		JsonDataFactory jfAxis = JsonDataFactory.instance();
		jfAxis.axisRange(LocalDateTime.now(), LocalDateTime.now());
		jfAxis.axisRange(LocalDateTime.now(), LocalDateTime.now());
		
		cli.demo();
		cli.jsf();
		cli.app();
	}
}