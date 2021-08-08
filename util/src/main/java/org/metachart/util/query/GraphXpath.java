package org.metachart.util.query;

import java.util.List;

import org.apache.commons.jxpath.JXPathContext;
import org.metachart.xml.graph.Cluster;
import org.metachart.xml.graph.Graph;
import org.metachart.xml.graph.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.exception.ExlpXpathNotFoundException;
import net.sf.exlp.exception.ExlpXpathNotUniqueException;

public class GraphXpath
{
	final static Logger logger = LoggerFactory.getLogger(GraphXpath.class);

	public static synchronized Node getNodeForCategory(Node root, String category) throws ExlpXpathNotFoundException, ExlpXpathNotUniqueException
	{
		JXPathContext context = JXPathContext.newContext(root);

		StringBuffer sb = new StringBuffer();
		sb.append("node[@category='").append(category).append("']");

		@SuppressWarnings("unchecked")
		List<Node> listResult = context.selectNodes(sb.toString());
		if(listResult.size()==0){throw new ExlpXpathNotFoundException("No "+Node.class.getSimpleName()+" for category="+category+" in XML");}
		else if(listResult.size()>1){throw new ExlpXpathNotUniqueException("Multiple "+Node.class.getSimpleName()+" for category="+category);}
		return listResult.get(0);
	}

	public static synchronized Cluster getClusterForName(Graph root, String name) throws ExlpXpathNotFoundException, ExlpXpathNotUniqueException
	{
		JXPathContext context = JXPathContext.newContext(root);

		StringBuffer sb = new StringBuffer();
		sb.append("//cluster[@category='").append(name).append("']");

		@SuppressWarnings("unchecked")
		List<Cluster> listResult = context.selectNodes(sb.toString());
		if(listResult.size()==0){throw new ExlpXpathNotFoundException("No "+Node.class.getSimpleName()+" for name="+name+" in XML");}
		else if(listResult.size()>1){throw new ExlpXpathNotUniqueException("Multiple "+Node.class.getSimpleName()+" for label="+name);}
		return listResult.get(0);
	}

}