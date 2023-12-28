package org.metachart.factory.xml.graph;

import org.metachart.model.xml.graph.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlNodeFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlNodeFactory.class);
	
	public static Node build(String code, String label)
	{
		return build(new Long(code.hashCode()),label);
	}
	
	public static Node build(long id) {return build(id,null);}
	public static Node build(long id, String label)
	{
		Node xml = new Node();
		xml.setId(id);
		xml.setLabel(label);
		return xml;
	}
}