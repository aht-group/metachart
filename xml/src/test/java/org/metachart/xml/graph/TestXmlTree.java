package org.metachart.xml.graph;

import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestXmlTree extends AbstractXmlGraphTest<Tree>
{
	final static Logger logger = LoggerFactory.getLogger(TestXmlTree.class);
	
	public TestXmlTree(){super(Tree.class);}
	public static Tree create(boolean withChildren){return (new TestXmlTree()).build(withChildren);}
    
    public Tree build(boolean withChilds)
    {
    	Tree xml = new Tree();
    	
    	if(withChilds)
    	{
    		xml.setNode(TestXmlNode.create(false));
    	}
    	
    	return xml;
    }
	
	public static void main(String[] args)
    {
		McBootstrap.init();
		TestXmlTree test = new TestXmlTree();
		test.saveReferenceXml();
    }
}