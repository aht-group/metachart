package org.metachart.model.json.chart.echart.label;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonDetail implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("valueAnimation")
	private Boolean valueAnimation;
	public Boolean getValueAnimation() {return valueAnimation;}
	public void setValueAnimation(Boolean valueAnimation) {this.valueAnimation = valueAnimation;}

	@JsonProperty("color")
	private String color;
	public String getColor() {return color;}
	public void setColor(String color) {this.color = color;}

	@JsonProperty("formatter")
	private String formatter;
	public String getFormatter() {return formatter;}
	public void setFormatter(String formatter) {this.formatter = formatter;}
}