package org.metachart.factory.graph;

import org.metachart.interfaces.graph.GraphColorProvider;
import org.metachart.xml.graph.Node;

public class DotNodeFactory 
{
	private final GraphColorProvider csm;
	
	public DotNodeFactory(GraphColorProvider csm)
	{
		this.csm=csm;
	}
	
	public String nodeToDot(Node n)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("  ").append(n.getId());
		sb.append(" [");
		sb.append("label=\""+n.getLabel()+"\",");
		sb.append("shape=record,");
		sb.append("style=filled,");
		sb.append("fixedsize=false,");
		sb.append(csm.getColor(n));
		sb.append(getFontSize(n));

		sb.deleteCharAt(sb.length()-1);
		sb.append("];");
		return sb.toString();
	}
	
	private String getFontSize(Node n)
	{
		StringBuffer sb = new StringBuffer();
		if(n.isSetSize())
		{
			if(n.getSize()==3)
			{
				sb.append("fontsize=14,");
			}
		}
		return sb.toString();
	}
}
