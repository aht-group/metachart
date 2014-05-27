package org.metachart.xml.graph;

import java.io.FileNotFoundException;

import net.sf.exlp.util.xml.JaxbUtil;

import org.junit.BeforeClass;
import org.junit.Test;
import org.metachart.test.McXmlTestBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestXmlNode extends AbstractXmlGraphTest
{
	final static Logger logger = LoggerFactory.getLogger(TestXmlNode.class);
	
	@BeforeClass public static void initFiles(){setXmlFile(dirSuffix, Node.class);}
    
    @Test
    public void testAclContainer() throws FileNotFoundException
    {
    	Node actual = create(true);
    	Node expected = JaxbUtil.loadJAXB(fXml.getAbsolutePath(), Node.class);
    	assertJaxbEquals(expected, actual);
    }
    
    public static Node create(boolean withChilds)
    {
    	Node xml = new Node();
    	xml.setId(123);
    	xml.setCode("myCode");
    	xml.setLabel("myLabel");
    	xml.setCategory("myCategory");
    	xml.setType("myType");
    	xml.setSize(1);
    	
    	if(withChilds)
    	{
    		xml.getNode().add(TestXmlNode.create(false));
    	}
    	
    	return xml;
    }
    
    public void save() {save(create(true),fXml);}
	
	public static void main(String[] args)
    {
		McXmlTestBootstrap.init();
			
		TestXmlNode.initFiles();
		TestXmlNode test = new TestXmlNode();
		test.save();
    }
}