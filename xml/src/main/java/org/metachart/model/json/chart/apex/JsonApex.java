package org.metachart.model.json.chart.apex;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonApex implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("id")
	private String id;
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
		
	@JsonProperty("chart")
	private JsonChart chart;
	public JsonChart getChart() {return chart;}
	public void setChart(JsonChart chart) {this.chart = chart;}
	
	@JsonProperty("title")
	private JsonTitle title;
	public JsonTitle getTitle() {return title;}
	public void setTitle(JsonTitle title) {this.title = title;}
	
	@JsonProperty("series")
	private List<JsonSeries> series;
	public List<JsonSeries> getSeries() {return series;}
	public void setSeries(List<JsonSeries> series) {this.series = series;}
}