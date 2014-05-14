package org.metachart.processor.graph;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import net.sf.exlp.util.io.StringIO;

import org.metachart.xml.graph.Edge;
import org.metachart.xml.graph.Graph;
import org.metachart.xml.graph.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Graph2DotConverter
{
	final static Logger logger = LoggerFactory.getLogger(Graph2DotConverter.class);
	
	private final static String ls = "\n";
	private final static String q = "\"";
	
	private StringBuffer sb;
	private String code,label;
	
	private Map<Long,Node> mapNodes;
	
	public Graph2DotConverter(String code, String label)
	{
		this.code=code;
		this.label=label;
		
		mapNodes = new Hashtable<Long,Node>();
		sb = new StringBuffer();
	}
	
	public String convert(Graph ofxGraph)
	{
		for(Node n : ofxGraph.getNodes().getNode()){mapNodes.put(n.getId(), n);}
		
		sb.append("digraph ").append(label).append(" {").append(ls);
		
		for(Edge e : ofxGraph.getEdges().getEdge())
		{
			Node nSrc = mapNodes.get(e.getFrom());
			Node nDst = mapNodes.get(e.getTo());
			
			sb.append(q).append(nSrc.getLabel()).append(q);
			sb.append(" -> ");
			sb.append(q).append(nDst.getLabel()).append(q);
			sb.append(" ");
			
			if(!e.isDirected()){sb.append("[dir=none]");}
		
			sb.append(";").append(ls);
		}
		
		sb.append("}").append(ls);
		return sb.toString();	
	}
	
	public void save(File f)
	{
		StringIO.writeTxt(f, sb.toString());
	}
}
