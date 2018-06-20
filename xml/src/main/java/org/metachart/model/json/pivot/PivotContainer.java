package org.metachart.model.json.pivot;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PivotContainer implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("fields")
	private PivotFields fields;
	public PivotFields getFields() {return fields;}
	public void setFields(PivotFields fields) {this.fields = fields;}
	
	@JsonProperty("fieldList")
	private PivotFieldList fieldList;
	public PivotFieldList getFieldList() {return fieldList;}
	public void setFieldList(PivotFieldList fieldList) {this.fieldList = fieldList;}
}