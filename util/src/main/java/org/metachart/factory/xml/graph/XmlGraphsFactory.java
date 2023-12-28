package org.metachart.factory.xml.graph;

import org.metachart.model.xml.graph.Graphs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlGraphsFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlGraphsFactory.class);
	
	public static Graphs build(){return new Graphs();}
}