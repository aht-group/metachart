package org.metachart.xml.chart;

import org.metachart.model.xml.chart.Title;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestXmlTitle extends AbstractXmlChartTest<Title>
{
	final static Logger logger = LoggerFactory.getLogger(TestXmlTitle.class);
	
	public TestXmlTitle(){super(Title.class);}
	public static Title create(boolean withChildren){return (new TestXmlTitle()).build(withChildren);}
    
    public Title build(boolean withChilds)
    {
    	Title xml = new Title();
    	xml.setKey("myKey");
    	xml.setLabel("myLabel");
    	
    	return xml;
    }
	
	public static void main(String[] args)
    {
		McBootstrap.init();
		TestXmlTitle test = new TestXmlTitle();
		test.saveReferenceXml();
    }
}