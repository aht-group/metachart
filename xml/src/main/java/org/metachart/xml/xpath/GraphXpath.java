package org.metachart.xml.xpath;

import java.util.List;

import net.sf.exlp.exception.ExlpXpathNotFoundException;
import net.sf.exlp.exception.ExlpXpathNotUniqueException;

import org.apache.commons.jxpath.JXPathContext;
import org.metachart.xml.graph.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphXpath
{
	final static Logger logger = LoggerFactory.getLogger(GraphXpath.class);
	
	public static synchronized Node getNodeForCategory(Node root, String category) throws ExlpXpathNotFoundException, ExlpXpathNotUniqueException
	{
		JXPathContext context = JXPathContext.newContext(root);
		
		StringBuffer sb = new StringBuffer();
		sb.append("node[@category='").append(category).append("']");
		
		@SuppressWarnings("unchecked")
		List<Node> listResult = (List<Node>)context.selectNodes(sb.toString());
		if(listResult.size()==0){throw new ExlpXpathNotFoundException("No "+Node.class.getSimpleName()+" for category="+category+" in XML");}
		else if(listResult.size()>1){throw new ExlpXpathNotUniqueException("Multiple "+Node.class.getSimpleName()+" for category="+category);}
		return listResult.get(0);
	}
	
	public static synchronized Node getNodeForCode(Node root, String code) throws ExlpXpathNotFoundException, ExlpXpathNotUniqueException
	{
		JXPathContext context = JXPathContext.newContext(root);
		
		StringBuffer sb = new StringBuffer();
		sb.append("node[@code='").append(code).append("']");
		
		@SuppressWarnings("unchecked")
		List<Node> listResult = (List<Node>)context.selectNodes(sb.toString());
		if(listResult.size()==0){throw new ExlpXpathNotFoundException("No "+Node.class.getSimpleName()+" for code="+code+" in XML");}
		else if(listResult.size()>1){throw new ExlpXpathNotUniqueException("Multiple "+Node.class.getSimpleName()+" for label="+code);}
		return listResult.get(0);
	}
}