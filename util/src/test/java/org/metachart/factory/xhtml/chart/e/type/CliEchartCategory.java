package org.metachart.factory.xhtml.chart.e.type;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;

import org.exlp.interfaces.system.property.Configuration;
import org.jdom2.Document;
import org.jdom2.Element;
import org.metachart.factory.json.chart.EchartProvider;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoCategoryLine;
import org.metachart.factory.json.chart.echart.js.line.JsonEchartCategoryFactory;
import org.metachart.factory.xhtml.chart.e.AbstractCliEchart;
import org.metachart.test.McBootstrap;
import org.metachart.util.provider.data.EchartLineCategoryDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.xml.JDomUtil;

public class CliEchartCategory extends AbstractCliEchart
{
	final static Logger logger = LoggerFactory.getLogger(CliEchartCategory.class);

	public CliEchartCategory(Configuration config)
	{
		type = JsonEchartFactory.Type.line;
		logger.info("Wrting to "+McBootstrap.pTemp.toString());
	}

	public void demo() throws IOException
	{
		StringWriter sw = new StringWriter();
		EchartProvider.instance(sw).demo(type,xfEchart.getDivId());
		this.render(sw,McBootstrap.pTemp.resolve("echart-"+type.toString()+".demo.html"));
	}
	
	public void jsf() throws IOException
	{
		EchartLineCategoryDataProvider dp = EchartLineCategoryDataProvider.instance();
		dp.categories(EchartDemoCategoryLine.toCategoriesX());
		dp.data(EchartDemoCategoryLine.toData());
		
		StringWriter sw = new StringWriter();
		JsonEchartCategoryFactory f = JsonEchartCategoryFactory.instance(sw).id(xfEchart.getDivId()); 
		f.jsf(xfEchart.getDivId(), null, dp);
		this.render(sw,McBootstrap.pTemp.resolve("echart-"+type.toString()+".jsf.html"));
	}

	public static void main (String[] args) throws Exception
	{
		Configuration config = McBootstrap.init();
		CliEchartCategory cli = new CliEchartCategory(config);

//		cli.demo();
		cli.jsf();
	}
}