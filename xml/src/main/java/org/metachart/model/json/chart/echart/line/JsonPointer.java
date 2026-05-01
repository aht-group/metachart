package org.metachart.model.json.chart.echart.line;

import java.io.Serializable;

import org.metachart.model.json.chart.echart.style.JsonItemStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonPointer implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("itemStyle")
	private JsonItemStyle itemStyle;
	public JsonItemStyle getItemStyle() {return itemStyle;}
	public void setItemStyle(JsonItemStyle itemStyle) {this.itemStyle = itemStyle;}
}