package org.metachart.factory.xhtml.chart.e.line;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.exlp.interfaces.system.property.Configuration;
import org.jdom2.Document;
import org.jdom2.Element;
import org.metachart.factory.json.chart.EchartProvider;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.js.demo.EchartDemoCategoryLine;
import org.metachart.factory.json.chart.echart.js.type.category.JsonEchartCategoryLineFactory;
import org.metachart.factory.xhtml.XhtmlEchartFactory;
import org.metachart.test.McBootstrap;
import org.metachart.util.provider.data.EchartLineCategoryDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.xml.JDomUtil;

public class CliEchartLineCategory
{
	final static Logger logger = LoggerFactory.getLogger(CliEchartLineCategory.class);

	private final Path pDir;
	private Path pFile;
	private final XhtmlEchartFactory xfEchart;
	private final JsonEchartFactory.Type type;

	public CliEchartLineCategory(Configuration config)
	{
		xfEchart = XhtmlEchartFactory.instance();

		type = JsonEchartFactory.Type.line;
		pDir = Paths.get(config.getString(McBootstrap.cfgDirTmp));
		logger.info("Wrting to "+pDir.toString());
	}
	
	private void render(StringWriter w) throws IOException
	{
		Element html = new Element("html");
		html.setAttribute("lang","en");
		html.getChildren().add(xfEchart.head("Demo: "+type));
		
		html.getChildren().add(xfEchart.body(w.toString()));

        Document doc = new Document(html);
        doc.setDocType(new org.jdom2.DocType("html"));

		JDomUtil.instance().omitDeclaration(true).info(doc);
		JDomUtil.instance().omitDeclaration(true).write(doc,pFile);
	}

	public void demo() throws IOException
	{
		pFile = pDir.resolve("echart-"+type.toString()+".demo.html");
		StringWriter sw = new StringWriter();
		EchartProvider.instance(sw).demo(type,xfEchart.getDivId());
		this.render(sw);
	}
	
	public void jsf() throws IOException
	{
		pFile = pDir.resolve("echart-"+type.toString()+".jsf.html");
		
		EchartLineCategoryDataProvider dp = EchartLineCategoryDataProvider.instance();
		dp.categories(EchartDemoCategoryLine.toCategoriesX());
		dp.data(EchartDemoCategoryLine.toData());
		
		StringWriter sw = new StringWriter();
		JsonEchartCategoryLineFactory f = JsonEchartCategoryLineFactory.instance(sw).id("id"); 
//		f.jsf("id", null, dp);
		this.render(sw);
	}

	public static void main (String[] args) throws Exception
	{
		Configuration config = McBootstrap.init();
		CliEchartLineCategory cli = new CliEchartLineCategory(config);

		cli.demo();
		cli.jsf();
	}
}