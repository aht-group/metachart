package org.metachart.model.json.chart.apache;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonSeries implements Serializable
{
	public static final long serialVersionUID=1;

	@JsonProperty("name")
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	@JsonProperty("type")
	private String type;
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	
	@JsonProperty("showSymbol")
	private Boolean showSymbol;
	public Boolean getShowSymbol() {return showSymbol;}
	public void setShowSymbol(Boolean showSymbol) {this.showSymbol = showSymbol;}
	
	@JsonProperty("data")
	private String data;
	public String getData() {return data;}
	public void setData(String data) {this.data = data;}
}