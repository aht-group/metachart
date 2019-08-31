package org.metachart.factory.xml.graph;

import org.metachart.xml.graph.Cluster;
import org.metachart.xml.graph.Clusters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlClustersFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlClustersFactory.class);
	
	public static Clusters build()
	{
		return new Clusters();
	}
	
	public static Clusters build(Cluster cluster)
	{
		Clusters xml = build();
		xml.getCluster().add(cluster);
		return xml;
	}
}