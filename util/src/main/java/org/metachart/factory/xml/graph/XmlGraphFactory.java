package org.metachart.factory.xml.graph;

import org.metachart.xml.graph.Clusters;
import org.metachart.xml.graph.Edges;
import org.metachart.xml.graph.Graph;
import org.metachart.xml.graph.Nodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlGraphFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlGraphFactory.class);
	
	public static Graph build(Nodes nodes, Edges edges){return build(nodes,edges,null);}
	public static Graph build(Nodes nodes, Edges edges, Clusters clusters)
	{
		Graph g = new Graph();
		g.setNodes(nodes);
		g.setEdges(edges);
		g.setClusters(clusters);
		return g;
	}
}