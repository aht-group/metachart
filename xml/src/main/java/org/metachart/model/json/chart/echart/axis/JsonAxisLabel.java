package org.metachart.model.json.chart.echart.axis;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonAxisLabel implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("color")
	private String color;
	public String getColor() {return color;}
	public void setColor(String color) {this.color = color;}

	@JsonProperty("distance")
	private Integer distance;
	public Integer getDistance() {return distance;}
	public void setDistance(Integer distance) {this.distance = distance;}
	
	@JsonProperty("fontSize")
	private Integer fontSize;
	public Integer getFontSize() {return fontSize;}
	public void setFontSize(Integer fontSize) {this.fontSize = fontSize;}

	
}