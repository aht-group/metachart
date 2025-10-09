package org.metachart.model.json.util;

import java.io.Serializable;

import org.metachart.model.json.graph.mc.JsonCategory;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonBrewerColor implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("id")
	private String id;
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	
	@JsonProperty("category")
	private JsonCategory category;
	public JsonCategory getCategory() {return category;}
	public void setCategory(JsonCategory category) {this.category = category;}

	@JsonProperty("description")
	private String description;
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	@JsonProperty("maximumColorCount")
	private Integer maximumColorCount;
	public Integer getMaximumColorCount() {return maximumColorCount;}
	public void setMaximumColorCount(Integer maximumColorCount) {this.maximumColorCount = maximumColorCount;}
}