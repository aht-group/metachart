package org.metachart.xml.graph;

import org.metachart.model.xml.graph.Node;
import org.metachart.test.McBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestXmlNode extends AbstractXmlGraphTest<Node>
{
	final static Logger logger = LoggerFactory.getLogger(TestXmlNode.class);
	
	public TestXmlNode(){super(Node.class);}
	public static Node create(boolean withChildren){return (new TestXmlNode()).build(withChildren);}
    
    public Node build(boolean withChilds)
    {
    	Node xml = new Node();
    	xml.setId(123l);
    	xml.setCode("myCode");
    	xml.setLabel("myLabel");
    	xml.setCategory("myCategory");
    	xml.setType("myType");
    	xml.setColor("myColor");
    	
    	xml.setSize(1);
    	xml.setSizeRelative(true);
    	xml.setSizeAdjustsColor(true);
    	
    	if(withChilds)
    	{
    		xml.getNode().add(TestXmlNode.create(false));
    	}
    	
    	return xml;
    }
	
	public static void main(String[] args)
    {
		McBootstrap.init();
		TestXmlNode test = new TestXmlNode();
		test.saveReferenceXml();
    }
}