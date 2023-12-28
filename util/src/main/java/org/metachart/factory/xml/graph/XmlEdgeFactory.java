package org.metachart.factory.xml.graph;

import org.metachart.model.xml.graph.Edge;
import org.metachart.model.xml.graph.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlEdgeFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlEdgeFactory.class);
	
	public static Edge build(long from, long to)
	{
		return build(from,to,true);
	}
	
	public static Edge build(long from, String to)
	{
		return build(from,new Long((to).hashCode()),true);
	}
	
	public static Edge build(String from, String to)
	{
		return build(new Long(from.hashCode()),new Long(to.hashCode()),true);
	}
	
	public static Edge build(long from, long to, boolean directed)
	{
		Edge xml = new Edge();
		xml.setFrom(from);
		xml.setTo(to);
		xml.setDirected(directed);
		return xml;
	}
	
	public static Edge build(Node from, Node to)
	{
		return build(from.getId(),to.getId(),true);
	}
}