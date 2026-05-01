package org.metachart.model.json.chart.echart.axis;

import java.io.Serializable;

import org.metachart.model.json.chart.echart.line.JsonLineStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonAxisLine implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("lineStyle")
	private JsonLineStyle lineStyle;
	public JsonLineStyle getLineStyle() {return lineStyle;}
	public void setLineStyle(JsonLineStyle lineStyle) {this.lineStyle = lineStyle;}
	
}