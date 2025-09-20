package org.metachart.model.json.chart.echart.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonDatas implements Serializable
{
	public static final long serialVersionUID=1;
	
//	private JsonData() {}
	
	@JsonProperty("list")
	private List<JsonData> list;
	public List<JsonData> getList() {return list;}
	public void setList(List<JsonData> list) {this.list = list;}
}