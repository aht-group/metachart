package org.metachart.model.json.chart.apache;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonAxis implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("type")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}