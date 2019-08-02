package org.metachart.processor.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.sf.exlp.util.io.txt.ExlpTxtWriter;

import org.metachart.xml.graph.Edge;
import org.metachart.xml.graph.Graph;
import org.metachart.xml.graph.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	public Graph2DotConverter(String label)
	{
		this.label=label;
		
		csm = new ColorSchemeManager();
		mapNodes = new Hashtable<Long,Node>();
		
		txtWriter = new ExlpTxtWriter();
		sb = new StringBuffer();
	}
	
	public String convert(Graph graph)
	{
		for(Node n : graph.getNodes().getNode()){mapNodes.put(n.getId(), n);}
		
		txtWriter.add("digraph "+label+" { ");
		txtWriter.add("");
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
		
		buildNodeDefinition(graph);
		
		for(Edge e : graph.getEdges().getEdge())
		{
			Node nSrc = mapNodes.get(e.getFrom());
			Node nDst = mapNodes.get(e.getTo());
			
			sb.append(q).append(nSrc.getId()).append(q);
			sb.append(" -> ");
			sb.append(q).append(nDst.getId()).append(q);
			sb.append(" ");
			
			if(!e.isDirected()){sb.append("[dir=none]");}
			else if(e.getType().equals("OneToMany")) {sb.append("[arrowhead = invempty, arrowtail=none, dir=both]");}
			else if(e.getType().equals("ManyToOne")) {sb.append("[arrowtail = invempty, arrowhead=none, dir=both]");}
			else if(e.getType().equals("ManyToMany")) {sb.append("[arrowtail = invempty, arrowhead=invempty, dir=both]");}
		
			sb.append(";").append(ls);
		}
		
		sb.append("}").append(ls);
		txtWriter.add(sb.toString());
		return sb.toString();	
	}
	
	private void buildNodeDefinition(Graph g)
	{
		Map<String, List<Node>>  mapNodesCategories = groupGraphNode(g);
		int NodeCategoryId = 0;
		for (Map.Entry<String, List<Node>> entry : mapNodesCategories.entrySet()) {
			boolean skipCatagorization  = false;
			if(entry.getKey() == "NA" || entry.getValue().size() < 2) {skipCatagorization = true;}
			if(!skipCatagorization) {
				StringBuffer sbCatBegin = new StringBuffer();
				sbCatBegin.append(" ").append(" subgraph ").append(" cluster_").append(NodeCategoryId).append("{ ");				
				txtWriter.add(sbCatBegin.toString());
				txtWriter.add(" label =<\n" + 
						"       <U><FONT FACE=\"cursive\" POINT-SIZE=\"30\"> "
						+ entry.getKey() 
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
	
	public String nodeToDot(Node n) {
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
			String subCategory  = node.getCategory();

			if (subCategory == null){subCategory="NA";}else if (subCategory.length() == 0) {subCategory="NA";}
		    if(mapNodesCategories.containsKey(subCategory)){
		        List<Node> subCategoryList = mapNodesCategories.get(subCategory);
		        subCategoryList.add(node);
			}
		    else {
		    	List<Node> subCategoryList = new ArrayList<Node>();
		    	subCategoryList.add(node);
		    	mapNodesCategories.put(subCategory, subCategoryList);
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
		csm = new ColorSchemeManager(xml);
	}
	public void setRatio(double ratio) {this.ratio=ratio;}
	public void setRanksep(double ranksep) {this.ranksep = ranksep;}
	public void setOverlap(boolean overlap) {this.overlap = overlap;}
}
