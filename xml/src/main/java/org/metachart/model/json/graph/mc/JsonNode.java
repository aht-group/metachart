package org.metachart.model.json.graph.mc;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonNode implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("id")
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	@JsonProperty("label")
	private String label;
	public String getLabel() {return label;}
	public void setLabel(String label) {this.label = label;}
	
	@JsonProperty("category")
	private JsonCategory category;
	public JsonCategory getCategory() {return category;}
	public void setCategory(JsonCategory category) {this.category = category;}
	
	@Override public boolean equals(Object object) {return (object instanceof JsonNode) ? id.longValue() == ((JsonNode) object).getId().longValue() : (object == this);}
//	@Override public int hashCode() {return new HashCodeBuilder(19,21).append(id).toHashCode();}
}