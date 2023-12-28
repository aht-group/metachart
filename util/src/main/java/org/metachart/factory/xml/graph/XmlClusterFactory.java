package org.metachart.factory.xml.graph;

import org.metachart.model.xml.graph.Cluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlClusterFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlClusterFactory.class);
	
	public static Cluster build(String code, String label)
	{
		Cluster xml = new Cluster();
		xml.setCode(code);
		xml.setLabel(label);
		return xml;
	}
}