package org.metachart.model.json.pivot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PivotFieldList implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("fieldList")
	private List<PivotField> fields;
	public List<PivotField> getFields() {if(fields==null) {fields = new ArrayList<PivotField>();}return fields;}
	public void setFields(List<PivotField> fields) {this.fields = fields;}
}