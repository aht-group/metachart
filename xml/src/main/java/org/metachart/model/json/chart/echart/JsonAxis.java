package org.metachart.model.json.chart.echart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonAxis implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("type")
	private String type;
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	
	@JsonProperty("data")
	private String data;
	public String getData() {return data;}
	public void setData(String data) {this.data = data;}

}