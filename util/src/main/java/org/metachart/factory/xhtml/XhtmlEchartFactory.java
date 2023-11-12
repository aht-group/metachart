package org.metachart.factory.xhtml;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;

import org.jdom2.Document;
import org.jdom2.Element;

import net.sf.exlp.util.xml.JDomUtil;

public class XhtmlEchartFactory
{
	private String divCntainerId; public String getDivCntainerId() {return divCntainerId;} public void setDivCntainerId(String divCntainerId) {this.divCntainerId = divCntainerId;}
	
	public static XhtmlEchartFactory instance() {return new XhtmlEchartFactory();}
	private XhtmlEchartFactory()
	{
		divCntainerId = "chart-container";
	}
	
	public void write(Path path, StringWriter w) throws IOException
	{
		Element html = new Element("html");
		html.setAttribute("lang","en");
		html.getChildren().add(this.head("Demo: "));
		html.getChildren().add(this.body(w.toString()));

        Document doc = new Document(html);
        doc.setDocType(new org.jdom2.DocType("html"));
		
//		JDomUtil.instance().omitDeclaration(true).info(doc);
		JDomUtil.instance().omitDeclaration(true).write(doc,path);
	}
	
	public Element head(String title)
	{
		Element metaCharset = new Element("meta"); metaCharset.setAttribute("charset","UTF-8");
		Element eTitle = new Element("title"); eTitle.addContent(title);
		Element script = new Element("script"); script.setText("cx"); script.setAttribute("src","https://fastly.jsdelivr.net/npm/echarts@5.4.3/dist/echarts.min.js");
		
		
		Element css = new Element("style");
		css.setText("* {margin: 0; padding: 0;}"
				+ "\n#chart-container {position: relative; height: 100vh; overflow: hidden;}"
				+ "\n.metachart-echart {border: 1px dotted blue;}");
		
		Element head = new Element("head");
		head.getChildren().add(metaCharset);
		head.getChildren().add(eTitle);
		head.getChildren().add(script);
		head.getChildren().add(css);
		return head;
	}
	
	public Element body(String script) throws IOException
	{
		Element div = new Element("div");
		div.setAttribute("id",divCntainerId);
		div.setAttribute("class","metachart-echart");
		
		Element eScript = new Element("script");
		eScript.addContent(script);
		
		Element body = new Element("body");
		body.getChildren().add(div);
		body.getChildren().add(eScript);
		return body;
	}
}