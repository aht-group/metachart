package org.metachart.model.json.chart.echart.grid;

import java.io.Serializable;
import java.util.List;

import org.metachart.model.json.chart.echart.data.JsonData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonMarkArea implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("data")
	private List<JsonData>[] data;
	public List<JsonData>[] getData() {return data;}
	public void setData(List<JsonData>[] data) {this.data = data;}
}