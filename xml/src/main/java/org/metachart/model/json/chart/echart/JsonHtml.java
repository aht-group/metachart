package org.metachart.model.json.chart.echart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonHtml implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("renderer")
	private String renderer;
	public String getRenderer() {return renderer;}
	public void setRenderer(String renderer) {this.renderer = renderer;}
	
	@JsonProperty("useDirtyRect")
	private Boolean useDirtyRect;
	public Boolean getUseDirtyRect() {return useDirtyRect;}
	public void setUseDirtyRect(Boolean useDirtyRect) {this.useDirtyRect = useDirtyRect;}

	@JsonProperty("width")
	private String width;
	public String getWidth() {return width;}
	public void setWidth(String width) {this.width = width;}

	@JsonProperty("height")
	private String height;
	public String getHeight() {return height;}
	public void setHeight(String height) {this.height = height;}
}