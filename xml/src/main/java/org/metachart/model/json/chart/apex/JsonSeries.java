package org.metachart.model.json.chart.apex;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonSeries implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("name")
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	@JsonProperty("data")
	private List<Integer[]> list;
	public List<Integer[]> getList() {
		return list;
	}
	public void setList(List<Integer[]> list) {
		this.list = list;
	}
}