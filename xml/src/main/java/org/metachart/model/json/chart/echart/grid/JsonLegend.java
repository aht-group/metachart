package org.metachart.model.json.chart.echart.grid;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonLegend implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("show")
	private Boolean show;
	public Boolean getShow() {return show;}
	public void setShow(Boolean show) {this.show = show;}

	@JsonProperty("orient")
	private String orient;
	public String getOrient() {return orient;}
	public void setOrient(String orient) {this.orient = orient;}
}