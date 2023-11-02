package org.metachart.model.json.chart.echart.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonLink implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("source")
	private String source;
	public String getSource() {return source;}
	public void setSource(String source) {this.source = source;}

	@JsonProperty("target")
	private String target;
	public String getTarget() {return target;}
	public void setTarget(String target) {this.target = target;}
	
	@JsonProperty("value")
	private Double value;
	public Double getValue() {return value;}
	public void setValue(Double value) {this.value = value;}
	
}