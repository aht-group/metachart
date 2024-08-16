package org.metachart.model.json.chart.echart.data;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonTsData implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("name")
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	
    @JsonProperty("value")    
    private List<Object[]> value;
    public List<Object[]> getValue() {return value;}
    public void setValue(List<Object[]> value) {this.value = value;}
	
}