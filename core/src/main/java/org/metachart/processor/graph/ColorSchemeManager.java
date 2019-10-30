package org.metachart.processor.graph;

import java.util.ArrayList;
import java.util.List;

import org.metachart.interfaces.graph.GraphColorProvider;
import org.metachart.xml.graph.Cluster;
import org.metachart.xml.graph.Graph;
import org.metachart.xml.graph.Node;
import org.metachart.xml.xpath.GraphXpath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.exception.ExlpXpathNotFoundException;
import net.sf.exlp.exception.ExlpXpathNotUniqueException;

public class ColorSchemeManager implements GraphColorProvider
{
	final static Logger logger = LoggerFactory.getLogger(ColorSchemeManager.class);

	private Graph xml;
	private List<String> subSet;

	public ColorSchemeManager(){this(null,new ArrayList<String>());}

	public ColorSchemeManager(Graph xml,List<String> subSet)
	{
		this.xml=xml;
		this.subSet=subSet;
	}

	public List<String> getSubSet() {return subSet;} public void setSubSet(List<String> subSet) {this.subSet = subSet;}

	private String buildColorString(Cluster cluster, int colorAdjust)
	{
		long colorId = cluster.getId() + colorAdjust;
		String colorCode = cluster.getCode();
		boolean isDefaultColor = true;

		if(cluster.isSetNode())
		{
			logger.info("Cluster name : " + cluster.getCategory());
			logger.info("------------------------------------" );

			searchColorCode:
				for(String subType : this.subSet)
				{
					logger.info("Searching node sub cluster color...subType: '"+subType+"'");
					for(Node clusterSubType : cluster.getNode())
					{
						logger.info("clusterSubType: '"+clusterSubType.getType()+"'");
						if(clusterSubType.getType().equals(subType))
						{
							colorCode = clusterSubType.getCode();
							logger.info("using replace color : " +colorCode +" for: "+ cluster.getCategory());
							isDefaultColor = false;
							break searchColorCode;
						}
					}
				}
			logger.info("------------------------------------" );
		}

		if(isDefaultColor)
		{
			logger.info("No subcluster color found" );
			logger.info("Using default cluster color..." + colorCode);
		}

		StringBuffer sb = new StringBuffer();
		sb.append(" colorscheme=").append(colorCode).append(",");
		sb.append(" fillcolor=").append(colorId).append(",");
		return sb.toString();
	}

	@Override
	public String getColor(Node node)
	{

		logger.debug("Getting color for node name = "+node.getLabel() + " : category="+node.getCategory());

		int colorAdjust = 0;
		if(node.isSetSizeAdjustsColor() && node.isSizeAdjustsColor() && node.isSetSize())
		{
			if(node.isSetSizeRelative() && node.isSizeRelative()==false)
			{
				logger.error("adjust and rel=false not allowed");
			}
			else
			{
				colorAdjust = colorAdjust+node.getSize();
			}
		}

		if(xml!=null && node.isSetCategory())
		{
			try{
				Cluster cluster = GraphXpath.getClusterForName(xml,node.getCategory());
				return buildColorString(cluster, colorAdjust);
			}
			catch (ExlpXpathNotUniqueException e){logger.warn(e.getMessage());return "";}
			catch (ExlpXpathNotFoundException e) {logger.error(e.getMessage());return "";}
		}
		return "";
	}


	public String getLabelForCluster(String name)
	{
		try
		{
			Cluster cluster = GraphXpath.getClusterForName(xml,name);
			return cluster.getLabel().trim();
		}
		catch (ExlpXpathNotFoundException  e) {logger.error(e.getMessage());return toCamelCaseLabel(name);}
		catch (ExlpXpathNotUniqueException e)
		{
			logger.warn(e.getMessage());
			return name;
		}
		catch (NullPointerException e) {
			logger.error("No description for cluster=" + name);return toCamelCaseLabel(name);
		}
	}

	public List<Node>getMergeNodesForCluster(String name)
	{
		try
		{
			Cluster cluster = GraphXpath.getClusterForName(xml,name);
			for(String subType : this.subSet)
			{
				if(cluster.getMergedNodes().getType().equals(subType)) {return cluster.getMergedNodes().getNode();}
			}
		}
		catch (ExlpXpathNotFoundException e) {logger.error("No cluster defined for: " + name);}
		catch (ExlpXpathNotUniqueException e) {logger.error("Many clusters for : "+ name + " : written in xml file: ");}
		catch (NullPointerException e) {logger.info("No merged nodes for cluster name: "+ name);}
		return new ArrayList<Node>();
	}

	private String toCamelCaseLabel(String name)
	{
		try{
			return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		}catch (Exception e) {
			return name;
		}
	}

	@Override
	public String getLabelForCategory(String catCode) {
		return getLabelForCluster(catCode);
	}
}