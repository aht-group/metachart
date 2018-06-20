package org.metachart.model.json.pivot;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PivotValue implements Serializable
{
	public static final long serialVersionUID=1;

	public enum Method{sum,count}
	
	@JsonProperty("id")
	private String id;
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	
	@JsonProperty("method")
	private String method;
	public String getMethod() {return method;}
	public void setMethod(String method) {this.method = method;}
}