package org.metachart.factory.xhtml.chart.e;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;

import org.exlp.interfaces.system.property.Configuration;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonDatasFactory;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoScatter;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoTime;
import org.metachart.factory.json.chart.echart.js.family.JsonEchartScatterFactory;
import org.metachart.factory.json.chart.echart.js.family.JsonEchartTimeFactory;
import org.metachart.model.json.chart.echart.JsonEchart;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;
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
	
	public void data()
	{
		JsonDatas datas = EchartDemoScatter.toDatas();
		JsonUtil.info(datas);
		logger.info(JsonUtil.instance().toFormattedString(datas));
	}

	public void demo() throws IOException
	{
		StringWriter sw = new StringWriter();
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(sw,JsonUtil.instance()).declare(xfEchart.getDivId(),JsonHtmlFactory.build(JsonHtmlFactory.Renderer.canvas,false));
		EchartDemoScatter.instance(jfEchart).demo();
		jfEchart.init();
		this.render(false,sw,McBootstrap.pTemp.resolve("echart-"+type.toString()+".demo.html"));
	}
	
	public void jsf() throws IOException
	{
		JsonDatas datas = EchartDemoScatter.toDatas();
		JsonUtil.instance().write(datas, McBootstrap.pTemp.resolve("echart-"+type.toString()+".datas.json"));
		
		StringWriter sw = new StringWriter();
		JsonEchartScatterFactory f = JsonEchartScatterFactory.instance(sw).id(xfEchart.getDivId()); 
		f.json(null,datas,EchartDemoScatter.instance(null).toOption(false));
		this.render(false,sw,McBootstrap.pTemp.resolve("echart-"+type.toString()+".jsf.html"));
	}
	
	public void app() throws IOException
	{		
		JsonData[] datas = JsonUtil.instance().readArray(JsonData[].class,McBootstrap.pTemp.resolve("echart-"+JsonEchart.Type.time+".datas.json"));
		JsonOption option = JsonUtil.instance().read(JsonOption.class,McBootstrap.pTemp.resolve("echart-"+JsonEchart.Type.time+".option.json"));
		
		StringWriter sw = new StringWriter();
		JsonEchartTimeFactory f = JsonEchartTimeFactory.instance(sw).id(xfEchart.getDivId()); 
		f.json(null, JsonDatasFactory.build(datas), option);
		this.render(false,sw,McBootstrap.pTemp.resolve("echart-"+type.toString()+".app.html"));
	}

	public static void main (String[] args) throws Exception
	{
		Configuration config = McBootstrap.init();
		CliEchartScatter cli = new CliEchartScatter(config);

		JsonDataFactory jfAxis = JsonDataFactory.instance();
		jfAxis.axisRange(LocalDateTime.now(), LocalDateTime.now());
		jfAxis.axisRange(LocalDateTime.now(), LocalDateTime.now());
		
//		cli.data();
		cli.demo();
		cli.jsf();
//		cli.app();
	}
}