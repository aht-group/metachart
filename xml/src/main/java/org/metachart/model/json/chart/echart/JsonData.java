package org.metachart.model.json.chart.echart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonData implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("name")
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
}