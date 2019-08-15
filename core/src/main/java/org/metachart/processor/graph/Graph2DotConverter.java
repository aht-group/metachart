package org.metachart.processor.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.metachart.xml.graph.Edge;
import org.metachart.xml.graph.Graph;
import org.metachart.xml.graph.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.io.txt.ExlpTxtWriter;

public class Graph2DotConverter
{
	final static Logger logger = LoggerFactory.getLogger(Graph2DotConverter.class);

	private final static String ls = "\n";
	private final static String q = "\"";

	private ColorSchemeManager csm;

	private StringBuffer sb;
	private String label;

	private ExlpTxtWriter txtWriter;

	private Map<Long,Node> mapNodes;

	private Double ratio,ranksep;
	private Boolean overlap;
	private List<String> subSet;
	private Node xml;

	public Graph2DotConverter(String label)
	{
		this.label=label;

		csm = new ColorSchemeManager();
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
			Node nSrc = mapNodes.get(e.getFrom());
			Node nDst = mapNodes.get(e.getTo());

			sb.append(q).append(nSrc.getId()).append(q);
			sb.append(" -> ");
			sb.append(q).append(nDst.getId()).append(q);
			sb.append(" ");

			//if(!e.isDirected()){sb.append("[dir=none]");}
			if(e.getType().equals("OneToMany")) {sb.append("[arrowhead = invempty, arrowtail=none, dir=both]");}
			else if(e.getType().equals("ManyToOne")) {sb.append("[arrowtail = none, arrowhead=invempty, dir=both]");}
			else if(e.getType().equals("ManyToMany")) {sb.append("[arrowtail = invempty, arrowhead=invempty, dir=both]");}
			else if(!e.isDirected()){sb.append("[dir=none]");}

			sb.append(";").append(ls);
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
		for (Map.Entry<String, List<Node>> entry : mapNodesCategories.entrySet()) {
			boolean skipCatagorization  = false;
			if(entry.getKey() == "NA" || entry.getValue().size() < 2) {skipCatagorization = true;}
			if(!skipCatagorization) {
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
			for(Node n: entry.getValue()) {
				txtWriter.add(nodeToDot(n));
			}
			if(!skipCatagorization) {txtWriter.add(" } ");}
			NodeCategoryId++;
		}
	}

	private String getCategoryLabel(String categoryName) {
		return categoryName.substring(0, 1).toUpperCase() + categoryName.substring(1).toLowerCase();
	}

	private String nodeToDot(Node n) {
		StringBuffer sb = new StringBuffer();
		sb.append("  ").append(n.getId());
		sb.append(" [");
		sb.append("label=\""+n.getLabel()+"\",");
		sb.append("shape=record,");
		sb.append("style=filled,");
		sb.append("fixedsize=false,");
		sb.append(csm.getColor(n));
		sb.append(getFontSize(n));

		sb.deleteCharAt(sb.length()-1);
		sb.append("];");
		return sb.toString();
	}

	public Map<String, List<Node>>  groupGraphNode(Graph g)
	{
		Map<String, List<Node>>  mapNodesCategories = new Hashtable<String,List<Node>>();
		for(Node node : g.getNodes().getNode())
		{
			String category  = node.getCategory();

			if (category == null){category="NA";}else if (category.length() == 0) {category="NA";}
		    if(mapNodesCategories.containsKey(category)){
		        List<Node> subCategoryList = mapNodesCategories.get(category);
		        subCategoryList.add(node);
			}
		    else {
		    	List<Node> categoryList = new ArrayList<Node>();
		    	categoryList.add(node);
		    	mapNodesCategories.put(category, categoryList);
		    }
		}
		return mapNodesCategories;
	}

	private String getFontSize(Node n)
	{
		StringBuffer sb = new StringBuffer();
		if(n.isSetSize())
		{
			if(n.getSize()==3)
			{
				sb.append("fontsize=14,");
			}
		}
		return sb.toString();
	}

	public void save(File f)
	{
		txtWriter.writeFile(f);
//		StringIO.writeTxt(f, sb.toString());
	}

	public void setColorScheme(Node xml)
	{
		this.xml = xml;
		csm = new ColorSchemeManager(xml);
	}
	public void setRatio(double ratio) {this.ratio=ratio;}
	public void setRanksep(double ranksep) {this.ranksep = ranksep;}
	public void setOverlap(boolean overlap) {this.overlap = overlap;}

	public List<String> getSubSet() {
		return subSet;
	}

	public void setSubSet(List<String> subSet) {
		this.subSet = subSet;
	}
}
