package org.metachart.model.json.chart.echart.line;

import java.io.Serializable;

import org.metachart.model.json.chart.echart.color.Color;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonLineStyle implements Serializable
{
	public static final long serialVersionUID=1;
	
//	@JsonProperty("color")
//	private String color;
//	public String getColor() {return color;}
//	public void setColor(String color) {this.color = color;}
	
	@JsonProperty("color")
	private Color color;
	public Color getColor() {return color;}
	public void setColor(Color color) {this.color = color;}

	@JsonProperty("width")
	private Integer width;
	public Integer getWidth() {return width;}
	public void setWidth(Integer width) {this.width = width;}

	@JsonProperty("curveness")
	private Double curveness;
	public Double getCurveness() {return curveness;}
	public void setCurveness(Double curveness) {this.curveness = curveness;}


}