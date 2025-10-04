package org.metachart.factory.xhtml.chart.e;

import java.io.IOException;
import java.io.StringWriter;

import org.exlp.interfaces.system.property.Configuration;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.EchartProvider;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.JsonHtmlFactory;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoCategory;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoScatter;
import org.metachart.factory.json.chart.echart.js.line.JsonEchartCategoryFactory;
import org.metachart.model.json.chart.echart.JsonEchart;
import org.metachart.test.McBootstrap;
import org.metachart.util.provider.data.EchartCategoryDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CliEchartCategory extends AbstractCliEchart
{
	final static Logger logger = LoggerFactory.getLogger(CliEchartCategory.class);

	public CliEchartCategory(Configuration config)
	{
		type = JsonEchart.Type.category;
		logger.info("Wrting to "+McBootstrap.pTemp.toString());
	}

	public void demo() throws IOException
	{
		StringWriter sw = new StringWriter();
		JsonEchartFactory jfEchart = JsonEchartFactory.instance(sw,JsonUtil.instance()).declare(xfEchart.getDivId(),JsonHtmlFactory.build(JsonHtmlFactory.Renderer.canvas,false));
		EchartDemoCategory.instance(jfEchart).demo();
		jfEchart.init();
		this.render(sw,McBootstrap.pTemp.resolve("echart-"+type.toString()+".demo.html"));
	}
	
//	public void jsf() throws IOException
//	{
//		EchartCategoryDataProvider dp = EchartCategoryDataProvider.instance();
//		dp.categories(EchartDemoCategory.toCategoriesX());
//		dp.data(EchartDemoCategory.toData());
//		
//		StringWriter sw = new StringWriter();
//		JsonEchartCategoryFactory f = JsonEchartCategoryFactory.instance(sw).id(xfEchart.getDivId()); 
//		f.jsf(null, dp);
//		this.render(sw,McBootstrap.pTemp.resolve("echart-"+type.toString()+".jsf.html"));
//	}

	public static void main (String[] args) throws Exception
	{
		Configuration config = McBootstrap.init();
		CliEchartCategory cli = new CliEchartCategory(config);

		cli.demo();
//		cli.jsf();
	}
}