package org.metachart.factory.xml.graph;

import org.metachart.model.xml.graph.Nodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlNodesFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlNodesFactory.class);
	
	public static Nodes build()
	{
		return new Nodes();
	}
}