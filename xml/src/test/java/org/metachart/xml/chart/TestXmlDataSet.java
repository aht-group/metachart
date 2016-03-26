package org.metachart.xml.chart;

import java.io.FileNotFoundException;

import net.sf.exlp.util.xml.JaxbUtil;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestXmlDataSet extends AbstractXmlChartTest
{
	final static Logger logger = LoggerFactory.getLogger(TestXmlDataSet.class);
	
	@BeforeClass
	public static void initFiles()
	{
        setXmlFile(dirSuffix, DataSet.class);
	}
    
    @Test
    public void testAclContainer() throws FileNotFoundException
    {
        DataSet actual = create(true);
        DataSet expected = JaxbUtil.loadJAXB(fXml.getAbsolutePath(), DataSet.class);
    	assertJaxbEquals(expected, actual);
    }
    
    public static DataSet create(boolean withChilds)
    {
        DataSet xml = new DataSet();
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
    
    public void save() {save(create(true),fXml);}
	
	public static void main(String[] args)
    {
//		UtilsXmlTstBootstrap.init();
			
		TestXmlDataSet.initFiles();
		TestXmlDataSet test = new TestXmlDataSet();
		test.save();
    }
}