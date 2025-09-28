package org.metachart.model.json.chart.echart.ui;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonEmphasis implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("focus")
	private String focus;
	public String getFocus() {return focus;}
	public void setFocus(String focus) {this.focus = focus;}
	
	@JsonProperty("lineStyle")
	private JsonLineStyle lineStyle;
	public JsonLineStyle getLineStyle() {
		return lineStyle;
	}
	public void setLineStyle(JsonLineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}
}