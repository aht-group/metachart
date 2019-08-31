package org.metachart.processor.graph;

import java.util.List;

import org.metachart.factory.graph.DotEdgeFactory;
import org.metachart.factory.graph.DotNodeFactory;
import org.metachart.interfaces.graph.GraphColorProvider;
import org.metachart.xml.graph.Edge;
import org.metachart.xml.graph.Graph;
import org.metachart.xml.graph.Node;

import net.sf.exlp.util.io.txt.ExlpTxtWriter;

public class Graph2DotConverter 
{
	private final ExlpTxtWriter txtWriter;
	
	private DotNodeFactory dotNode;
	private DotEdgeFactory dotEdge;
	
	public Graph2DotConverter(GraphColorProvider csm)
	{
		txtWriter = new ExlpTxtWriter();
		
		dotNode = new DotNodeFactory(csm);
		dotEdge = new DotEdgeFactory(csm);
	}
	
	public List<String> build(Graph graph)
	{
		txtWriter.clear();
		txtWriter.add("digraph "+graph.getCode()+" { ");
		
		for(Node n: graph.getNodes().getNode())
		{
			txtWriter.add(dotNode.nodeToDot(n));
		}
		
		for(Edge e : graph.getEdges().getEdge())
		{
			txtWriter.add(dotEdge.build(e));
		}
		
		txtWriter.add("}");
		txtWriter.writeStream(System.out);
		return null;
	}
}
