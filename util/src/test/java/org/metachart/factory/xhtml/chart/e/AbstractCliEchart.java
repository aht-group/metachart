package org.metachart.factory.xhtml.chart.e;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;

import org.jdom2.Document;
import org.jdom2.Element;
import org.metachart.factory.xhtml.XhtmlEchartFactory;
import org.metachart.model.json.chart.echart.JsonEchart;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.xml.JDomUtil;

public class AbstractCliEchart
{
	final static Logger logger = LoggerFactory.getLogger(AbstractCliEchart.class);

	protected final XhtmlEchartFactory xfEchart;
	protected JsonEchart.Type type;

	public AbstractCliEchart()
	{
		xfEchart = XhtmlEchartFactory.instance();
		logger.info("Writing to "+McBootstrap.pTemp.toString());
	}
	
	protected void render(StringWriter w, Path pFile) throws IOException
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

}