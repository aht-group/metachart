package org.metachart.processor.graph;

import net.sf.exlp.exception.ExlpXpathNotFoundException;
import net.sf.exlp.exception.ExlpXpathNotUniqueException;

import java.util.ArrayList;
import java.util.List;

import org.metachart.xml.graph.Node;
import org.metachart.xml.xpath.GraphXpath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColorSchemeManager
{
	final static Logger logger = LoggerFactory.getLogger(ColorSchemeManager.class);
	
	private Node xml;
	private List<String> subSet;
	
	public ColorSchemeManager(){this(null);}
	
	public ColorSchemeManager(Node xml)
	{
		this.xml=xml;
		this.subSet=new ArrayList<String>();
	}
	
	public List<String> getSubSet() {
		return subSet;
	}

	public void setSubSet(List<String> subSet) {
		this.subSet = subSet;
	}

	private String buildColorString(Node category, Node entity, int colorAdjust)
	{
		long colorId = entity.getId() + colorAdjust;
		String colorCode = category.getCode();
		boolean isDefaultColor = true;
		
		if(entity.isSetNode()) {
			logger.trace("Entity category : " + entity.getCategory());
			logger.trace("Category category : " + category.getCategory());
			logger.trace(category.toString());
			
			logger.trace("------------------------------------" );
	
			searchColorCode:
				for(String subType : this.subSet) {
					logger.trace("Searching node sub category color...subType : " + subType );
						for(Node categorySubType: category.getNode()) {
							logger.trace("categorySubType : " + categorySubType.getType() );
							if(categorySubType.getType().equals(subType)) {
								colorCode = categorySubType.getCode();
								logger.trace("using replace color : " +colorCode +" for: "+ category.getCategory() );
								isDefaultColor = false;
								break searchColorCode;
							}
						}
				}
			logger.trace("------------------------------------" );
			}
		
		if(isDefaultColor) {
			logger.trace("No subcategory color found" );
			logger.trace("Using default category color..." + colorCode);
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(" colorscheme=").append(colorCode).append(",");
		sb.append(" fillcolor=").append(colorId).append(",");
		return sb.toString();
	}
	
	public String getColor(Node node)
	{
		logger.info("Getting color for "+node.getLabel() + " : "+node.getCategory());
		
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
}
