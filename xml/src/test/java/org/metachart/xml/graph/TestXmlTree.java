package org.metachart.xml.graph;

import java.io.FileNotFoundException;

import net.sf.exlp.util.xml.JaxbUtil;

import org.junit.BeforeClass;
import org.junit.Test;
import org.metachart.test.McXmlTestBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestXmlTree extends AbstractXmlGraphTest
{
	final static Logger logger = LoggerFactory.getLogger(TestXmlTree.class);
	
	@BeforeClass public static void initFiles(){setXmlFile(dirSuffix, Tree.class);}
    
    @Test
    public void testAclContainer() throws FileNotFoundException
    {
    	Tree actual = create(true);
    	Tree expected = JaxbUtil.loadJAXB(fXml.getAbsolutePath(), Tree.class);
    	assertJaxbEquals(expected, actual);
    }
    
    public static Tree create(boolean withChilds)
    {
    	Tree xml = new Tree();
    	
    	if(withChilds)
    	{
    		xml.setNode(TestXmlNode.create(false));
    	}
    	
    	return xml;
    }
    
    public void save() {save(create(true),fXml);}
	
	public static void main(String[] args)
    {
		McXmlTestBootstrap.init();
			
		TestXmlTree.initFiles();
		TestXmlTree test = new TestXmlTree();
		test.save();
    }
}