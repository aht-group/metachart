package org.metachart.interfaces.graph;

import org.metachart.model.xml.graph.Node;

public interface GraphColorProvider
{
	String getColor(Node node);
	String getLabelForCategory(String catCode);
}
