package org.metachart.model.json.chart.echart.data;

import java.io.Serializable;

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
}