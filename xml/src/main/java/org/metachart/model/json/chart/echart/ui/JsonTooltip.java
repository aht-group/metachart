package org.metachart.model.json.chart.echart.ui;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonTooltip implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("trigger")
	private String trigger;
	public String getTrigger() {return trigger;}
	public void setTrigger(String trigger) {this.trigger = trigger;}

	@JsonProperty("position")
	private String position;
	public String getPosition() {return position;}
	public void setPosition(String position) {this.position = position;}
	
	@JsonProperty("formatter")
	private String formatter;
	public String getFormatter() {return formatter;}
	public void setFormatter(String formatter) {this.formatter = formatter;}
}