package org.metachart.factory.xml.graph;

import org.metachart.model.xml.graph.Edges;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlEdgesFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlEdgesFactory.class);
	
	public static Edges build()
	{
		return new Edges();
	}
}