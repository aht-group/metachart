package org.metachart.model.json.chart.echart.ui;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonLabel implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("color")
	private String color;
	public String getColor() {return color;}
	public void setColor(String color) {this.color = color;}


	@JsonProperty("fontFamily")
	private String fontFamily;
	
	public String getFontFamily() {
		return fontFamily;
	}
	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}


	@JsonProperty("fontSize")
	private Integer fontSize;
	public Integer getFontSize() {return fontSize;}
	public void setFontSize(Integer fontSize) {this.fontSize = fontSize;}


}