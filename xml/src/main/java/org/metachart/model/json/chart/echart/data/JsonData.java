package org.metachart.model.json.chart.echart.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class JsonData implements Serializable
{
	public static final long serialVersionUID=1;
	
//	private JsonData() {}
	
	@JsonProperty("id")
	private String id;
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}

	@JsonProperty("name")
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	@JsonProperty("category")
	private Integer category;
	public Integer getCategory() {return category;}
	public void setCategory(Integer category) {this.category = category;}

	@JsonProperty("strings")
	private String[] strings;
	public String[] getStrings() {return strings;}
	public void setStrings(String[] strings) {this.strings = strings;}
	
	@JsonProperty("times")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
	@JsonDeserialize(contentUsing = LocalDateTimeDeserializer.class)
	@JsonSerialize(contentUsing = LocalDateTimeSerializer.class)
	private LocalDateTime[] times;
	public LocalDateTime[] getTimes() {return times;}
	public void setTimes(LocalDateTime[] times) {this.times = times;}

	@JsonProperty("doubles1")
	private double[] doubles1;
	public double[] getDoubles1() {return doubles1;}
	public void setDoubles1(double[] doubles1) {this.doubles1 = doubles1;}
	
	@JsonProperty("doubles2")
	private double[][] doubles2;
	public double[][] getDoubles2() {return doubles2;}
	public void setDoubles2(double[][] doubles2) {this.doubles2 = doubles2;}
	
	@JsonProperty("areas")
	private List<JsonData>[] areas;
	public List<JsonData>[] getAreas() {return areas;}
	public void setAreas(List<JsonData>[] areas) {this.areas = areas;}

	@JsonProperty("data")
	private List<JsonData> data;
	public List<JsonData> getData() {return data;}
	public void setData(List<JsonData> data) {this.data = data;}
	
	@JsonProperty("edges")
	private List<JsonEdge> edges;
	public List<JsonEdge> getEdges() {return edges;}
	public void setEdges(List<JsonEdge> edges) {this.edges = edges;}
	
	@JsonProperty("xAxis")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
	@JsonDeserialize(contentUsing = LocalDateTimeDeserializer.class)
	@JsonSerialize(contentUsing = LocalDateTimeSerializer.class)
	private LocalDateTime xAxis;
	public LocalDateTime getxAxis() {return xAxis;}
	public void setxAxis(LocalDateTime xAxis) {this.xAxis = xAxis;}
}