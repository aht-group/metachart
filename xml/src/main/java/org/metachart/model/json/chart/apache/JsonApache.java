package org.metachart.model.json.chart.apache;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonApache implements Serializable
{
	public static final long serialVersionUID=1;
	
			
	@JsonProperty("title")
	private JsonTitle title;
	public JsonTitle getTitle() {return title;}
	public void setTitle(JsonTitle title) {this.title = title;}
	
	@JsonProperty("xAxis")
	private JsonAxis axisX;
	public JsonAxis getAxisX() {return axisX;}
	public void setAxisX(JsonAxis axisX) {this.axisX = axisX;}

	@JsonProperty("yAxis")
	private JsonAxis axisY;
	public JsonAxis getAxisY() {return axisY;}
	public void setAxisY(JsonAxis axisY) {this.axisY = axisY;}
	
	@JsonProperty("series")
	private List<JsonSeries> series;
	public List<JsonSeries> getSeries() {return series;}
	public void setSeries(List<JsonSeries> series) {this.series = series;}
}