package org.metachart.xml.chart;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.text.View;

import net.sf.exlp.util.xml.JaxbUtil;

import org.junit.BeforeClass;
import org.junit.Test;
import org.metachart.xml.Title;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestXmlView extends AbstractXmlAccessTest
{
	final static Logger logger = LoggerFactory.getLogger(TestXmlView.class);
	
	@BeforeClass
	public static void initFiles()
	{
		fXml = new File(rootDir,"view.xml");
	}
    
    @Test
    public void testAclContainer() throws FileNotFoundException
    {
    	Title actual = create(true);
    	Title expected = JaxbUtil.loadJAXB(fXml.getAbsolutePath(), Title.class);
    	assertJaxbEquals(expected, actual);
    }
    
    public static Title create(boolean withChilds)
    {
    	Title xml = new Title();
    	xml.setKey("myKey");
    	xml.setLabel("myLabel");
    	
    	return xml;
    }
    
    public void save() {save(create(true),fXml);}
	
	public static void main(String[] args)
    {
//		UtilsXmlTstBootstrap.init();
			
		TestXmlView.initFiles();	
		TestXmlView test = new TestXmlView();
		test.save();
    }
}