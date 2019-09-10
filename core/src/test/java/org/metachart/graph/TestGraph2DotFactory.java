package org.metachart.graph;

import org.metachart.factory.xml.graph.XmlClusterFactory;
import org.metachart.factory.xml.graph.XmlClustersFactory;
import org.metachart.factory.xml.graph.XmlEdgeFactory;
import org.metachart.factory.xml.graph.XmlEdgesFactory;
import org.metachart.factory.xml.graph.XmlGraphFactory;
import org.metachart.factory.xml.graph.XmlNodeFactory;
import org.metachart.factory.xml.graph.XmlNodesFactory;
import org.metachart.processor.graph.ColorSchemeManager;
import org.metachart.processor.graph.Graph2DotConverter;
import org.metachart.xml.graph.Cluster;
import org.metachart.xml.graph.Clusters;
import org.metachart.xml.graph.Edges;
import org.metachart.xml.graph.Graph;
import org.metachart.xml.graph.Nodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestGraph2DotFactory
{
	final static Logger logger = LoggerFactory.getLogger(TestGraph2DotFactory.class);

	private Graph2DotConverter dgf;

	public TestGraph2DotFactory()
	{
		dgf = new Graph2DotConverter(new ColorSchemeManager());
	}

	public void simple()
	{
		Graph g = provide(false);
		//JaxbUtil.info(g);
		dgf.build(g, "Test");
		dgf.print();
	}

	private void cluster()
	{
		Graph g = provide(true);
		//JaxbUtil.info(g);
		dgf.build(g,"Test");
		dgf.print();
	}

	private Graph provide(boolean withCluster)
	{
		Nodes nodes = XmlNodesFactory.build();
		nodes.getNode().add(XmlNodeFactory.build(1,"Start"));
		nodes.getNode().add(XmlNodeFactory.build(2,"Implementing"));
		nodes.getNode().add(XmlNodeFactory.build(3,"Testing"));
		nodes.getNode().add(XmlNodeFactory.build(4,"Finished"));
		nodes.getNode().add(XmlNodeFactory.build(5,"Abort"));

		Edges edges = XmlEdgesFactory.build();
		edges.getEdge().add(XmlEdgeFactory.build(nodes.getNode().get(0), nodes.getNode().get(1)));
		edges.getEdge().add(XmlEdgeFactory.build(nodes.getNode().get(1), nodes.getNode().get(2)));
		edges.getEdge().add(XmlEdgeFactory.build(nodes.getNode().get(2), nodes.getNode().get(1)));
		edges.getEdge().add(XmlEdgeFactory.build(nodes.getNode().get(2), nodes.getNode().get(3)));
		edges.getEdge().add(XmlEdgeFactory.build(nodes.getNode().get(1), nodes.getNode().get(4)));
		edges.getEdge().add(XmlEdgeFactory.build(nodes.getNode().get(2), nodes.getNode().get(4)));

		Cluster cluster = XmlClusterFactory.build("dev", "Development Phase");
		cluster.getNode().add(XmlNodeFactory.build(nodes.getNode().get(1).getId()));
		cluster.getNode().add(XmlNodeFactory.build(nodes.getNode().get(2).getId()));
		Clusters clusters = XmlClustersFactory.build(cluster);

		Graph graph = XmlGraphFactory.build(nodes,edges,clusters);
		graph.setCode("test");
		if(!withCluster) {graph.setClusters(null);}
		return graph;
	}

	public static void main(String[] args)
    {
//		UtilsXmlTstBootstrap.init();

		TestGraph2DotFactory cli = new TestGraph2DotFactory();
		//cli.simple();
		cli.cluster();
    }
}