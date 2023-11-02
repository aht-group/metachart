package org.metachart.model.json.chart.echart.ui;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonVisualMap implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("show")
	private Boolean show;
	public Boolean getShow() {return show;}
	public void setShow(Boolean show) {this.show = show;}

	@JsonProperty("min")
	private Integer min;
	public Integer getMin() {return min;}
	public void setMin(Integer min) {this.min = min;}
	
	@JsonProperty("max")
	private Integer max;
	public Integer getMax() {return max;}
	public void setMax(Integer max) {this.max = max;}
	
	@JsonProperty("orient")
	private String orient;
	public String getOrient() {return orient;}
	public void setOrient(String orient) {this.orient = orient;}
	
}