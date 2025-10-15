package org.metachart.model.json.chart.echart;

import java.io.Serializable;
import java.util.List;

import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.metachart.model.json.chart.echart.grid.JsonAxis;
import org.metachart.model.json.chart.echart.grid.JsonGrid;
import org.metachart.model.json.chart.echart.grid.JsonLegend;
import org.metachart.model.json.chart.echart.grid.JsonTitle;
import org.metachart.model.json.chart.echart.ui.JsonDataZoom;
import org.metachart.model.json.chart.echart.ui.JsonTooltip;
import org.metachart.model.json.chart.echart.ui.JsonVisualMap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonOption implements Serializable
{
	public static final long serialVersionUID=1;
	
			
	@JsonProperty("title")
	private JsonTitle title;
	public JsonTitle getTitle() {return title;}
	public void setTitle(JsonTitle title) {this.title = title;}
	
	@JsonProperty("grid")
	private JsonGrid grid;
	public JsonGrid getGrid() {return grid;}
	public void setGrid(JsonGrid grid) {this.grid = grid;}

	@JsonProperty("xAxis")
	private JsonAxis axisX;
	public JsonAxis getAxisX() {return axisX;}
	public void setAxisX(JsonAxis axisX) {this.axisX = axisX;}

	@JsonProperty("yAxis")
	private JsonAxis axisY;
	public JsonAxis getAxisY() {return axisY;}
	public void setAxisY(JsonAxis axisY) {this.axisY = axisY;}
	
	@JsonProperty("useUTC")
	private Boolean useUtc;
	public Boolean getUseUtc() {return useUtc;}
	public void setUseUtc(Boolean useUtc) {this.useUtc = useUtc;}
	
	@JsonProperty("legend")
	private JsonLegend legend;
	public JsonLegend getLegend() {return legend;}
	public void setLegend(JsonLegend legend) {this.legend = legend;}

	@JsonProperty("tooltip")
	private JsonTooltip tooltip;
	public JsonTooltip getTooltip() {return tooltip;}
	public void setTooltip(JsonTooltip tooltip) {this.tooltip = tooltip;}
	
	@JsonProperty("visualMap")
	private JsonVisualMap visualMap;
	public JsonVisualMap getVisualMap() {return visualMap;}
	public void setVisualMap(JsonVisualMap visualMap) {this.visualMap = visualMap;}
	
	@JsonProperty("series")
	private List<JsonSeries> series;
	public List<JsonSeries> getSeries() {return series;}
	public void setSeries(List<JsonSeries> series) {this.series = series;}
	
	@JsonProperty("dataZoom")
	private List<JsonDataZoom> dataZoom;
	public List<JsonDataZoom> getDataZoom() {return dataZoom;}
	public void setDataZoom(List<JsonDataZoom> dataZoom) {this.dataZoom = dataZoom;}
	
}