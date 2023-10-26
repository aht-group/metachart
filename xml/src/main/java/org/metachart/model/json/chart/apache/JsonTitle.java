package org.metachart.model.json.chart.apache;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonTitle implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("text")
	private String text;
	public String getText() {return text;}
	public void setText(String text) {this.text = text;}
}