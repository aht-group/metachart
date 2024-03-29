package org.metachart.factory.graph;

import java.util.Objects;

import org.metachart.interfaces.graph.GraphColorProvider;
import org.metachart.model.xml.graph.Node;

public class DotNodeFactory
{
	private final GraphColorProvider csm;

	public DotNodeFactory(GraphColorProvider csm)
	{
		this.csm=csm;
	}

	public String nodeToDot(Node n)
	{
		return nodeToDot(n,false);
	}

	public String nodeToDot(Node n, boolean isClusterNode)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("  ").append(n.getId());
		if(!isClusterNode) {
			sb.append(" [");
			sb.append("label=\""+n.getLabel()+"\",");
			sb.append("shape=record,");
			sb.append("style=filled,");
			sb.append("fixedsize=false,");
			sb.append(csm.getColor(n));
			sb.append(getFontSize(n));
			sb.deleteCharAt(sb.length()-1);
			sb.append("];");
		}
		return sb.toString();
	}

	private String getFontSize(Node n)
	{
		StringBuffer sb = new StringBuffer();
		if(Objects.nonNull(n.getSize()))
		{
			if(n.getSize()==3)
			{
				sb.append("fontsize=14,");
			}
		}
		return sb.toString();
	}
}
