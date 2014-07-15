package org.metachart.processor.graph;

import net.sf.exlp.exception.ExlpXpathNotFoundException;
import net.sf.exlp.exception.ExlpXpathNotUniqueException;

import org.metachart.xml.graph.Node;
import org.metachart.xml.xpath.GraphXpath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColorSchemeManager
{
	final static Logger logger = LoggerFactory.getLogger(ColorSchemeManager.class);
	
	private Node xml;
	
	public ColorSchemeManager(){this(null);}
	
	public ColorSchemeManager(Node xml)
	{
		this.xml=xml;
	}
	
	public String getColor(Node node)
	{
		logger.info("Getting color for "+node.getLabel());
		
		int colorAdjust = 0;
		if(node.isSetSizeAdjustsColor() && node.isSizeAdjustsColor())
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
			try
			{
				Node category = GraphXpath.getNodeForCategory(xml, node.getCategory());
				
				try
				{
					Node entity = GraphXpath.getNodeForCode(category, node.getLabel());
					return buildColorString(category, entity,colorAdjust);
				}
				catch (ExlpXpathNotFoundException e) {return buildColorString(category, category,colorAdjust);}
				catch (ExlpXpathNotUniqueException e)
				{
					logger.warn(e.getMessage());
					return "";
				}
			}
			catch (ExlpXpathNotFoundException e) {logger.error(e.getMessage());return "";}
			catch (ExlpXpathNotUniqueException e)
			{
				logger.warn(e.getMessage());
				return "";
			}
		}
		
		return "";
	}
	
	private String buildColorString(Node category, Node entity, int colorAdjust)
	{
		long colorId = entity.getId() + colorAdjust;
		StringBuffer sb = new StringBuffer();
		sb.append(" colorscheme=").append(category.getCode()).append(",");
		sb.append(" fillcolor=").append(colorId).append(",");
		return sb.toString();
	}
}
