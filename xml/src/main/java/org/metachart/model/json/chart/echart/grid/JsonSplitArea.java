package org.metachart.model.json.chart.echart.grid;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonSplitArea implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("show")
	private Boolean show;
	public Boolean getShow() {return show;}
	public void setShow(Boolean show) {this.show = show;}
}