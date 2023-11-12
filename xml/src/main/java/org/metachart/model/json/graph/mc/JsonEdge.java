package org.metachart.model.json.graph.mc;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonEdge implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("source")
	private JsonNode source;
	public JsonNode getSource() {return source;}
	public void setSource(JsonNode source) {this.source = source;}

	@JsonProperty("destination")
	private JsonNode destination;
	public JsonNode getDestination() {return destination;}
	public void setDestination(JsonNode destination) {this.destination = destination;}
}