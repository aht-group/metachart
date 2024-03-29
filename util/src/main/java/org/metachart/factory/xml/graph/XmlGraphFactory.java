package org.metachart.factory.xml.graph;

import org.metachart.model.xml.graph.Clusters;
import org.metachart.model.xml.graph.Edges;
import org.metachart.model.xml.graph.Graph;
import org.metachart.model.xml.graph.Nodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlGraphFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlGraphFactory.class);
	
	public static Graph build(){return new Graph();}
	
	public static Graph build(Nodes nodes, Edges edges){return build(nodes,edges,null);}
	public static Graph build(Nodes nodes, Edges edges, Clusters clusters)
	{
		Graph g = build();
		g.setNodes(nodes);
		g.setEdges(edges);
		g.setClusters(clusters);
		return g;
	}
	
	public static Graph build(String code)
	{
		Graph g = build();
		g.setCode(code);
		return g;
	}
}