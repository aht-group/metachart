package org.metachart.model.json.chart.apex;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonToolbar implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("show")
	private Boolean show;
	public Boolean getShow() {return show;}
	public void setShow(Boolean show) {this.show = show;}
	
}