package org.metachart.factory.graph;

import org.exlp.util.io.txt.ExlpTxtWriter;
import org.metachart.interfaces.graph.GraphColorProvider;
import org.metachart.model.xml.graph.Cluster;
import org.metachart.model.xml.graph.Node;

public class DotClusterFactory {
	private final GraphColorProvider csm;
	private ExlpTxtWriter txtWriter;
	private DotNodeFactory dotNode;
	private String fillcolor, style,color;
	private String formatedLabel;

	public DotClusterFactory(GraphColorProvider csm, ExlpTxtWriter txtWriter, DotNodeFactory dotNode) {
		this.csm=csm;
		this.txtWriter = txtWriter;
		this.dotNode = dotNode;
		//to-do init config
		this.fillcolor = "lightgrey";
		this.style = "filled,bold";
		this.color = "black";
		this.formatedLabel = " label =<\n <U><FONT FACE=\"Bookman Old Style\" POINT-SIZE=\"30\"> %s </FONT></U>\n >\n   labelloc=\"t\"";
	}

	public void clusterToDot(Cluster cluster) {
		StringBuffer sbCatBegin = new StringBuffer();
		sbCatBegin.append(" ").append(" subgraph ").append(" cluster_").append(cluster.getCode()).append("{ ");
		txtWriter.add(sbCatBegin.toString());
		txtWriter.add(" fillcolor=" + fillcolor +"; ");
		txtWriter.add(" style=\""+ style + "\"; ");
		txtWriter.add(" color=" + color +"; ");

		txtWriter.add(String.format(this.formatedLabel, this.csm.getLabelForCategory(cluster.getLabel())));
		for(Node node: cluster.getNode()) {
			txtWriter.add(dotNode.nodeToDot(node,true));
		}
		txtWriter.add(" } ");
	}
}
