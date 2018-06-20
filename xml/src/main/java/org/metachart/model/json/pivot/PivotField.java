package org.metachart.model.json.pivot;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PivotField implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("id")
	private String id;
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	
	@JsonProperty("label")
	private String label;
	public String getLabel() {return label;}
	public void setLabel(String label) {this.label = label;}
	
	@JsonProperty("aliases")
	private Map<Long,String> map;
	public Map<Long, String> getMap()
{
//		if(map==null) {map = new HashMap<Long,String>();}
		return map;}
	public void setMap(Map<Long, String> map) {this.map = map;}
	
	@JsonProperty("method")
	private String method;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
}