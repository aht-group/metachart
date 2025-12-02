package org.metachart.processor.graph;

import java.io.ByteArrayOutputStream;
import java.io.File;

import org.exlp.util.io.txt.ExlpTxtWriter;
import org.metachart.factory.graph.DotClusterFactory;
import org.metachart.factory.graph.DotEdgeFactory;
import org.metachart.factory.graph.DotGraphFactory;
import org.metachart.factory.graph.DotNodeFactory;
import org.metachart.interfaces.graph.GraphColorProvider;
import org.metachart.model.xml.graph.Cluster;
import org.metachart.model.xml.graph.Edge;
import org.metachart.model.xml.graph.Graph;
import org.metachart.model.xml.graph.Node;

public class Graph2DotConverter
{
	private final ExlpTxtWriter txtWriter;

	private DotNodeFactory dotNode;
	private DotEdgeFactory dotEdge;
	private DotClusterFactory dotCluster;

	private DotGraphFactory dotGraph;

	public Graph2DotConverter(GraphColorProvider csm)
	{
		txtWriter = new ExlpTxtWriter();

		dotNode = new DotNodeFactory(csm);
		dotEdge = new DotEdgeFactory(csm);
		dotCluster = new DotClusterFactory(csm,txtWriter, dotNode);
		dotGraph =  new DotGraphFactory(txtWriter);
	}

	public void save(File f) {txtWriter.writeFile(f);}

	public void build(Graph graph)
	{
		build(graph, "");
	}
	public void build(Graph graph, String label)
	{
		txtWriter.clear();
		dotGraph.beginDotDiagraph(label, graph);
		if(graph.getClusters() != null)
		{
			for(Cluster c : graph.getClusters().getCluster())
			{
				dotCluster.clusterToDot(c);
			}
		}

		for(Node n: graph.getNodes().getNode())
		{
			txtWriter.add(dotNode.nodeToDot(n));
		}

		for(Edge e : graph.getEdges().getEdge())
		{
			txtWriter.add(dotEdge.build(e));
		}

		dotGraph.endDotDiagraph();

		//txtWriter.writeStream(System.out);
	}

	public void print()
	{
		txtWriter.writeStream(System.out);
	}

	public String getDot()
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		txtWriter.writeStream(baos);
		return baos.toString();
	}

	public void initWorkflowDiagramSetting()
	{
		dotGraph.setingsWorkflowDiagaram();
	}
}