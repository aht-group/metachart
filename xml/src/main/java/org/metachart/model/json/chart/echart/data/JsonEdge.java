package org.metachart.model.json.chart.echart.data;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonEdge implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("source")
	private Integer source;
	public Integer getSource() {return source;}
	public void setSource(Integer source) {this.source = source;}

	@JsonProperty("target")
	private Integer target;
	public Integer getTarget() {return target;}
	public void setTarget(Integer target) {this.target = target;}
	
	@JsonProperty("edges")
	private List<JsonEdge> edges;
	public List<JsonEdge> getEdges() {return edges;}
	public void setEdges(List<JsonEdge> edges) {this.edges = edges;}
}