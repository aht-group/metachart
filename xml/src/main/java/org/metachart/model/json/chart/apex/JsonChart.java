package org.metachart.model.json.chart.apex;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonChart implements Serializable
{
	public static final long serialVersionUID=1;
	
	public enum Type {heatmap}
	
	@JsonProperty("type")
	private String type;
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}

	@JsonProperty("height")
	private Integer height;
	public Integer getHeight() {return height;}
	public void setHeight(Integer height) {this.height = height;}
	
	@JsonProperty("toolbar")
	private JsonToolbar toolbar;
	public JsonToolbar getToolbar() {return toolbar;}
	public void setToolbar(JsonToolbar toolbar) {this.toolbar = toolbar;}

}