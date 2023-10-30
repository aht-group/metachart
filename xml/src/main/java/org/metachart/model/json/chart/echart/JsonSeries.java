package org.metachart.model.json.chart.echart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonSeries implements Serializable
{
	public static final long serialVersionUID=1;

	@JsonProperty("type")
	private String type;
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	
	@JsonProperty("name")
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	@JsonProperty("showSymbol")
	private Boolean showSymbol;
	public Boolean getShowSymbol() {return showSymbol;}
	public void setShowSymbol(Boolean showSymbol) {this.showSymbol = showSymbol;}
	
	@JsonProperty("lineStyle")
	private JsonLineStyle lineStyle;
	public JsonLineStyle getLineStyle() {return lineStyle;}
	public void setLineStyle(JsonLineStyle lineStyle) {this.lineStyle = lineStyle;}

	@JsonProperty("label")
	private JsonLabel label;
	public JsonLabel getLabel() {return label;}
	public void setLabel(JsonLabel label) {this.label = label;}

	@JsonProperty("data")
	private String data;
	public String getData() {return data;}
	public void setData(String data) {this.data = data;}
	
	@JsonProperty("links")
	private String links;
	public String getLinks() {return links;}
	public void setLinks(String links) {this.links = links;}
}