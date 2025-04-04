package org.metachart.model.json.chart.echart.data;

import java.io.Serializable;

import org.metachart.model.json.chart.echart.ui.JsonEmphasis;
import org.metachart.model.json.chart.echart.ui.JsonLabel;
import org.metachart.model.json.chart.echart.ui.JsonLineStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonSeries implements Serializable
{
	public static final long serialVersionUID=1;

	@JsonProperty("type")
	private String type;
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	
	@JsonProperty("layout")
	private String layout;
	public String getLayout() {return layout;}
	public void setLayout(String layout) {this.layout = layout;}

	@JsonProperty("name")
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	@JsonProperty("showSymbol")
	private Boolean showSymbol;
	public Boolean getShowSymbol() {return showSymbol;}
	public void setShowSymbol(Boolean showSymbol) {this.showSymbol = showSymbol;}
	
	@JsonProperty("lineStyle")
	private JsonLineStyle lineStyle;
	public JsonLineStyle getLineStyle() {return lineStyle;}
	public void setLineStyle(JsonLineStyle lineStyle) {this.lineStyle = lineStyle;}
	
	@JsonProperty("animation")
	private Boolean animation;
	public Boolean getAnimation() {return animation;}
	public void setAnimation(Boolean animation) {this.animation = animation;}

	@JsonProperty("draggable")
	private Boolean draggable;
	public Boolean getDraggable() {return draggable;}
	public void setDraggable(Boolean draggable) {this.draggable = draggable;}

	@JsonProperty("label")
	private JsonLabel label;
	public JsonLabel getLabel() {return label;}
	public void setLabel(JsonLabel label) {this.label = label;}
	
	@JsonProperty("emphasis")
	private JsonEmphasis emphasis;
	public JsonEmphasis getEmphasis() {return emphasis;}
	public void setEmphasis(JsonEmphasis emphasis) {this.emphasis = emphasis;}

	@JsonProperty("categories")
	private String categories;
	public String getCategories() {return categories;}
	public void setCategories(String categories) {this.categories = categories;}

	@JsonProperty("min")
	private Double min;
	public Double getMin() {return min;}
	public void setMin(Double min) {this.min = min;}
	
	@JsonProperty("max")
	private Double max;
	public Double getMax() {return max;}
	public void setMax(Double max) {this.max = max;}

	@JsonProperty("data")
	private String data;
	public String getData() {return data;}
	public void setData(String data) {this.data = data;}
	
	@JsonProperty("links")
	private String links;
	public String getLinks() {return links;}
	public void setLinks(String links) {this.links = links;}
	
	@JsonProperty("edges")
	private String edges;
	public String getEdges() {return edges;}
	public void setEdges(String edges) {this.edges = edges;}
}