package org.metachart.model.json.graph.mc;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonGraph implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("nodes")
	private List<JsonNode> nodes;
	public List<JsonNode> getNodes() {return nodes;}
	public void setNodes(List<JsonNode> nodes) {this.nodes = nodes;}
	
	@JsonProperty("edges")
	private List<JsonEdge> edges;
	public List<JsonEdge> getEdges() {return edges;}
	public void setEdges(List<JsonEdge> edges) {this.edges = edges;}
}