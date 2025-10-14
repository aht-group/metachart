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
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoScatter;
import org.metachart.factory.json.chart.echart.js.family.JsonEchartScatterFactory;
import org.metachart.model.json.chart.echart.JsonEchart;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CliEchartScatter extends AbstractCliEchart
{
	final static Logger logger = LoggerFactory.getLogger(CliEchartScatter.class);

	public CliEchartScatter(Configuration config)
	{
		type = JsonEchart.Type.scatter;
	}
	
	public void demo() throws IOException
	{
		StringWriter sw = new StringWriter();
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(sw,JsonUtil.instance()).declare(xfEchart.getDivId(),JsonHtmlFactory.build(JsonHtmlFactory.Renderer.canvas,false));
		EchartDemoScatter.demo(jfEchart);
		jfEchart.init();
		super.render(true,sw,"demo");
	}
	
	public void jsf() throws IOException
	{
		JsonDatas datas = EchartDemoScatter.toDatas();
		JsonUtil.instance().write(datas, McBootstrap.pTemp.resolve("echart-"+type.toString()+".datas.json"));
		
		JsonOption option = EchartDemoScatter.toOption();
		option.setTooltip(null);
		
		StringWriter sw = new StringWriter();
		JsonEchartScatterFactory f = JsonEchartScatterFactory.instance(sw).id(xfEchart.getDivId()); 
		f.json(null,datas,option);
		super.render(true,sw,"jsf");
	}
	
	public void app() throws IOException
	{	
		Path p = McBootstrap.pTemp.resolve("echart-"+JsonEchart.Type.scatter+".chart.json");
		if(Files.exists(p))
		{
			JsonEchart chart = JsonUtil.instance().read(JsonEchart.class,p);
			
			StringWriter sw = new StringWriter();
			JsonEchartScatterFactory f = JsonEchartScatterFactory.instance(sw).id(xfEchart.getDivId()); 
			f.json(null, JsonDatasFactory.build(chart.getDatas()), chart.getOption());
			super.render(true,sw,"app");
		}
	}

	public static void main (String[] args) throws Exception
	{
		Configuration config = McBootstrap.init();
		CliEchartScatter cli = new CliEchartScatter(config);

		JsonDataFactory jfAxis = JsonDataFactory.instance();
		jfAxis.axisRange(LocalDateTime.now(), LocalDateTime.now());
		jfAxis.axisRange(LocalDateTime.now(), LocalDateTime.now());
		
		cli.demo();
		cli.jsf();
		cli.app();
	}
}