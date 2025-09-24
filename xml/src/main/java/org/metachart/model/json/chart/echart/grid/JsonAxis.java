package org.metachart.model.json.chart.echart.grid;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonAxis implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("show")
	private Boolean show;
	public Boolean getShow() {return show;}
	public void setShow(Boolean show) {this.show = show;}
	
	@JsonProperty("type")
	private String type;
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	
	@JsonProperty("data")
	private String data;
	public String getData() {return data;}
	public void setData(String data) {this.data = data;}
	
	@JsonProperty("min")
	private String min;

	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}

	@JsonProperty("splitArea")
	private JsonSplitArea splitArea;
	public JsonSplitArea getSplitArea() {return splitArea;}
	public void setSplitArea(JsonSplitArea splitArea) {this.splitArea = splitArea;}

	
}