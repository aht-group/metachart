package org.metachart.model.json.chart.echart.line;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonSplitLine implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("distance")
	private Integer distance;
	public Integer getDistance() {return distance;}
	public void setDistance(Integer distance) {this.distance = distance;}
	
	@JsonProperty("length")
	private Integer length;
	public Integer getLength() {return length;}
	public void setLength(Integer length) {this.length = length;}
	
	@JsonProperty("lineStyle")
	private JsonLineStyle lineStyle;
	public JsonLineStyle getLineStyle() {return lineStyle;}
	public void setLineStyle(JsonLineStyle lineStyle) {this.lineStyle = lineStyle;}
}