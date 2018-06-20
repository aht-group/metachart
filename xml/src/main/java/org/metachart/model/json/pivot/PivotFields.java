package org.metachart.model.json.pivot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PivotFields implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("rows")
	private List<String> rows;
	public List<String> getRows() {if(rows==null) {rows = new ArrayList<String>();}return rows;}
	public void setMap(List<String> rows) {this.rows = rows;}
	
	@JsonProperty("columns")
	private List<String> columns;
	public List<String> getColumns() {if(columns==null) {columns = new ArrayList<String>();}return columns;}
	public void setColumns(List<String> columns) {this.columns = columns;}
	
	@JsonProperty("values")
	private List<PivotValue> values;
	public List<PivotValue> getValues() {if(values==null) {values = new ArrayList<PivotValue>();}return values;}
	public void setValues(List<PivotValue> values) {this.values = values;}
}