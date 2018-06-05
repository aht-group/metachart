package org.metachart.model.json.pivot;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PivotAliases implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("aliases")
	private Map<Long,String> map;
	public Map<Long, String> getMap() {if(map==null) {map = new HashMap<Long,String>();}return map;}
	public void setMap(Map<Long, String> map) {this.map = map;}
}