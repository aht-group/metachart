package org.metachart.processor.graph;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.metachart.factory.graph.DotEdgeFactory;
import org.metachart.factory.graph.DotNodeFactory;
import org.metachart.xml.graph.Edge;
import org.metachart.xml.graph.Graph;
import org.metachart.xml.graph.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.io.txt.ExlpTxtWriter;

public class Graph1DotConverter
{
	final static Logger logger = LoggerFactory.getLogger(Graph1DotConverter.class);

	private final static String ls = "\n";

	private ColorSchemeManager csm;
	private DotNodeFactory dotNode;
	private DotEdgeFactory dotEdge;

	private StringBuffer sb;
	private String label;

	private ExlpTxtWriter txtWriter;

	private Map<Long,Node> mapNodes;

	private Double ratio,ranksep;
	private Boolean overlap;
	private List<String> subSet; public List<String> getSubSet() {return subSet;} public void setSubSet(List<String> subSet) {this.subSet = subSet;}

	public Graph1DotConverter(ColorSchemeManager csm, String label)
	{
		this.csm=csm;
		this.label=label;

		dotNode = new DotNodeFactory(csm);
		dotEdge = new DotEdgeFactory(csm);
		
		mapNodes = new Hashtable<Long,Node>();

		txtWriter = new ExlpTxtWriter();
		sb = new StringBuffer();
		subSet = new ArrayList<String>();
	}

	public String convert(Graph graph)
	{
		for(Node n : graph.getNodes().getNode()){mapNodes.put(n.getId(), n);}

		txtWriter.add("digraph "+label+" { ");
		txtWriter.add("");

		txtWriter.add(" label =<\n" +
				"       <U><FONT FACE=\"Times New Roman\" POINT-SIZE=\"40\"> "
				+ getCategoryLabel(subSet.get(0))
				+ "</FONT></U>\n" +
				"     >\n" +
				"     labelloc=\"b\"");

		txtWriter.add("");
		txtWriter.add("  mode=ipsep;");
		txtWriter.add("  model=subset;");
		txtWriter.add("  overlap=prism;");
		txtWriter.add("  nodesep=0.5;");
		txtWriter.add("  equally=true;");
		txtWriter.add("  pad=0.3;");
		txtWriter.add("  splines=ortho;");

		if(ratio!=null){txtWriter.add("  ratio="+ratio+";");}
		if(ranksep!=null){txtWriter.add("  ranksep="+ranksep+";");}
		if(overlap!=null){txtWriter.add("  overlap="+overlap+";");}
		txtWriter.add("");
		csm.setSubSet(subSet);

		buildNodeDefinition(graph);

		for(Edge e : graph.getEdges().getEdge())
		{
			sb.append(dotEdge.build(e)).append(ls);
		}

		sb.append("}").append(ls);
		txtWriter.add(sb.toString());
		return sb.toString();
	}

	private void buildNodeDefinition(Graph g)
	{
		Map<String, List<Node>>  mapNodesCategories = groupGraphNode(g);
		logger.trace("---" + mapNodesCategories.keySet().toString() +"---");
		int NodeCategoryId = 0;
		for(Map.Entry<String,List<Node>> entry : mapNodesCategories.entrySet())
		{
			boolean skipCatagorization  = false;
			if(entry.getKey() == "NA" || entry.getValue().size() < 2) {skipCatagorization = true;}
			if(!skipCatagorization)
			{
				StringBuffer sbCatBegin = new StringBuffer();
				sbCatBegin.append(" ").append(" subgraph ").append(" cluster_").append(NodeCategoryId).append("{ ");
				logger.trace("--" + entry.getKey() +"--");
				txtWriter.add(sbCatBegin.toString());
				txtWriter.add(" fillcolor=lightgrey; ");
				txtWriter.add(" style=\"filled,bold\"; ");
				txtWriter.add(" color=black; ");
				txtWriter.add(" label =<\n" +
						"       <U><FONT FACE=\"Bookman Old Style\" POINT-SIZE=\"30\"> "
						+ getCategoryLabel(entry.getKey())
						+ "</FONT></U>\n" +
						"     >\n" +
						"     labelloc=\"t\"");
			}
			for(Node n: entry.getValue())
			{
				txtWriter.add(dotNode.nodeToDot(n));
			}
			if(!skipCatagorization) {txtWriter.add(" } ");}
			NodeCategoryId++;
		}
	}

	private String getCategoryLabel(String categoryName)
	{
		return categoryName.substring(0, 1).toUpperCase() + categoryName.substring(1).toLowerCase();
	}

	public Map<String, List<Node>> groupGraphNode(Graph g)
	{
		Map<String, List<Node>>  mapNodesCategories = new Hashtable<String,List<Node>>();
		for(Node node : g.getNodes().getNode())
		{
			String category  = node.getCategory();

			if(category==null){category="NA";} else if (category.length() == 0) {category="NA";}
		    if(mapNodesCategories.containsKey(category))
		    {
		        List<Node> subCategoryList = mapNodesCategories.get(category);
		        subCategoryList.add(node);
			}
		    else
		    {
		    	List<Node> categoryList = new ArrayList<Node>();
		    	categoryList.add(node);
		    	mapNodesCategories.put(category, categoryList);
		    }
		}
		return mapNodesCategories;
	}

	public String getDot()
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		txtWriter.writeStream(baos);
		return baos.toString();
	}
	

	public void save(File f) {txtWriter.writeFile(f);}

	public void setRatio(double ratio) {this.ratio=ratio;}
	public void setRanksep(double ranksep) {this.ranksep = ranksep;}
	public void setOverlap(boolean overlap) {this.overlap = overlap;}	
}