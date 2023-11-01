package org.metachart.model.json.chart.echart.grid;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonGrid implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("show")
	private Boolean show;
	public Boolean getShow() {return show;}
	public void setShow(Boolean show) {this.show = show;}
	
	@JsonProperty("top")
	private String top;
	public String getTop() {return top;}
	public void setTop(String top) {this.top = top;}

	@JsonProperty("left")
	private String left;
	public String getLeft() {return left;}
	public void setLeft(String left) {this.left = left;}
	
	@JsonProperty("right")
	private String right;
	public String getRight() {return right;}
	public void setRight(String right) {this.right = right;}
	
	@JsonProperty("bottom")
	private String bottom;
	public String getBottom() {return bottom;}
	public void setBottom(String bottom) {this.bottom = bottom;}
}