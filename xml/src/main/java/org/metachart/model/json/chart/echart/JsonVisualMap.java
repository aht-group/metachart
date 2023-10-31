package org.metachart.model.json.chart.echart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonVisualMap implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("min")
	private Integer min;
	public Integer getMin() {return min;}
	public void setMin(Integer min) {this.min = min;}
	
	@JsonProperty("max")
	private Integer max;
	public Integer getMax() {return max;}
	public void setMax(Integer max) {this.max = max;}
}