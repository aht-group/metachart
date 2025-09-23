package org.metachart.factory.xhtml.chart.e.family;

import java.io.IOException;
import java.io.StringWriter;

import org.exlp.interfaces.system.property.Configuration;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.EchartProvider;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoTime;
import org.metachart.factory.json.chart.echart.js.line.JsonEchartTimeFactory;
import org.metachart.factory.xhtml.chart.e.AbstractCliEchart;
import org.metachart.model.json.chart.echart.JsonEchart;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.test.McBootstrap;
import org.metachart.util.provider.data.EchartTimeDataProvider;
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
	
	public void data()
	{
		JsonData data = EchartDemoTime.toData("A");
		JsonUtil.info(data);
		logger.info(JsonUtil.instance().toFormattedString(data));
	}

	public void demo() throws IOException
	{
		StringWriter sw = new StringWriter();
		EchartProvider.instance(sw).demo(type,xfEchart.getDivId());
		this.render(sw,McBootstrap.pTemp.resolve("echart-"+type.toString()+".demo.html"));
	}
	
	public void jsf() throws IOException
	{
		EchartTimeDataProvider dp = EchartTimeDataProvider.instance();
		dp.data(EchartDemoTime.toData("A"));
		dp.data(EchartDemoTime.toData("B"));
		JsonUtil.instance().write(dp.getDatas(), McBootstrap.pTemp.resolve("datas-"+type.toString()+".json"));
		
		StringWriter sw = new StringWriter();
		JsonEchartTimeFactory f = JsonEchartTimeFactory.instance(sw).id(xfEchart.getDivId()); 
		f.jsf(null, dp);
		this.render(sw,McBootstrap.pTemp.resolve("echart-"+type.toString()+".jsf.html"));
	}
	
	public void app() throws IOException
	{
		EchartTimeDataProvider dp = EchartTimeDataProvider.instance();
//		dp.data(EchartDemoTime.toData("A"));
		
		JsonData[] datas = JsonUtil.instance().readArray(JsonData[].class,McBootstrap.pTemp.resolve("echart-"+JsonEchart.Type.time+".app.json"));
		dp.datas(datas);
		
		StringWriter sw = new StringWriter();
		JsonEchartTimeFactory f = JsonEchartTimeFactory.instance(sw).id(xfEchart.getDivId()); 
		f.jsf(null, dp);
		this.render(sw,McBootstrap.pTemp.resolve("echart-"+type.toString()+".jsf.html"));
	}

	public static void main (String[] args) throws Exception
	{
		Configuration config = McBootstrap.init();
		CliEchartTime cli = new CliEchartTime(config);

		cli.data();
		cli.demo();
//		cli.jsf();
//		cli.app();
	}
}