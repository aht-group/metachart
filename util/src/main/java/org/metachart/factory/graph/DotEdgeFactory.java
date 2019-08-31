package org.metachart.factory.graph;

import org.metachart.interfaces.graph.GraphColorProvider;
import org.metachart.xml.graph.Edge;

public class DotEdgeFactory 
{
	private final GraphColorProvider csm;
	private final static String q = "\"";
	
	public DotEdgeFactory(GraphColorProvider csm)
	{
		this.csm=csm;
	}
	
	public String build(Edge edge)
	{
		StringBuilder sb = new StringBuilder();

		sb.append(q).append(edge.getFrom()).append(q);
		sb.append(" -> ");
		sb.append(q).append(edge.getTo()).append(q);
		sb.append(" [");

		if(edge.isSetLabel())
		{
			sb.append("label=<<i> ").append(edge.getLabel()).append("</i>>");
			sb.append(",fontsize=").append(q).append("8").append(q);
		}

		//if(!e.isDirected()){sb.append("[dir=none]");}
		if(edge.isSetType())
		{
			if(edge.getType().equals("OneToMany")) {sb.append("arrowhead = invempty, arrowtail=none, dir=both");}
			else if(edge.getType().equals("ManyToOne")) {sb.append("arrowtail = none, arrowhead=invempty, dir=both");}
			else if(edge.getType().equals("ManyToMany")) {sb.append("arrowtail = invempty, arrowhead=invempty, dir=both");}
		}
		else
		{
			if(!edge.isDirected()){sb.append("dir=none");}
		}

		sb.append("];");
		return sb.toString();
	}
}
