package org.metachart.processor.graph;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import net.sf.exlp.util.io.txt.ExlpTxtWriter;
import net.sf.exlp.util.xml.JaxbUtil;

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
	private String code,label;
	
	private ExlpTxtWriter txtWriter;
	
	private Map<Long,Node> mapNodes;
	
	private Double ratio,ranksep;
	private Boolean overlap;

	public Graph2DotConverter(String code, String label)
	{
		this.code=code;
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
		
		if(ratio!=null){txtWriter.add("  ratio="+ratio+";");}
		if(ranksep!=null){txtWriter.add("  ranksep="+ranksep+";");}
		if(overlap!=null){txtWriter.add("  overlap="+overlap+";");}
		txtWriter.add("");
		
		buildNodeDefinition(graph);
		
		for(Edge e : graph.getEdges().getEdge())
		{
			Node nSrc = mapNodes.get(e.getFrom());
			Node nDst = mapNodes.get(e.getTo());
			
			sb.append(q).append(nSrc.getLabel()).append(q);
			sb.append(" -> ");
			sb.append(q).append(nDst.getLabel()).append(q);
			sb.append(" ");
			
			if(!e.isDirected()){sb.append("[dir=none]");}
		
			sb.append(";").append(ls);
		}
		
		sb.append("}").append(ls);
		txtWriter.add(sb.toString());
		return sb.toString();	
	}
	
	private void buildNodeDefinition(Graph g)
	{
		for(Node n : g.getNodes().getNode())
		{
			StringBuffer sb = new StringBuffer();
			sb.append("  ").append(n.getLabel());
			sb.append(" [").append("style=filled,");
//			txtWriter.add(n.getLabel()+" [colorscheme=spectral9,  fillcolor=2];");
			sb.append(csm.getColor(n));
			sb.deleteCharAt(sb.length()-1);
			sb.append("];");
			txtWriter.add(sb.toString());
		}
		txtWriter.add("");
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
