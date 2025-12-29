package org.metachart.model.json.chart.orgchart.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonOptions implements Serializable
{
	public static final long serialVersionUID=1;

	@JsonProperty("nodeContent")
	private String nodeContent;
	public String getNodeContent() {return nodeContent;}
	public void setNodeContent(String nodeContent) {this.nodeContent = nodeContent;}
	
	@JsonProperty("data")
	private JsonData data;
	public JsonData getData() {return data;}
	public void setData(JsonData data) {this.data = data;}
}