package org.metachart.model.json.chart.orgchart.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class JsonData implements Serializable
{
	public static final long serialVersionUID=1;

	@JsonProperty("name")
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	@JsonProperty("title")
	private String title;
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}

	@JsonProperty("relationship")
	private String relationship;
	public String getRelationship() {return relationship;}
	public void setRelationship(String relationship) {this.relationship = relationship;}
	
	@JsonProperty("children")
	private List<JsonData> children;
	public List<JsonData> getChildren() {return children;}
	public void setChildren(List<JsonData> children) {this.children = children;}
}