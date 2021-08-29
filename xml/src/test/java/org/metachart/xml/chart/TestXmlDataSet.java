package org.metachart.xml.chart;

import org.metachart.test.McXmlTestBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestXmlDataSet extends AbstractXmlChartTest<Ds>
{
	final static Logger logger = LoggerFactory.getLogger(TestXmlDataSet.class);
	
	public TestXmlDataSet(){super(Ds.class);}
	public static Ds create(boolean withChildren){return (new TestXmlDataSet()).build(withChildren);}
    
    public Ds build(boolean withChilds)
    {
    	Ds xml = new Ds();
    	xml.setCode("myCode");
    	xml.setLabel("myLabel");
        xml.setColor("myColor");
        xml.setColorIndex(1);
        xml.setRangeIndex(2);

        if(withChilds)
        {
            logger.warn("NYI");
        }
    	
    	return xml;
    }
	
	public static void main(String[] args)
    {
		McXmlTestBootstrap.init();
		TestXmlDataSet test = new TestXmlDataSet();
		test.saveReferenceXml();
    }
}