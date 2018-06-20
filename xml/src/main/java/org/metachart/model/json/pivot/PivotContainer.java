package org.metachart.model.json.pivot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PivotContainer implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("fields")
	private PivotFields fields;
	public PivotFields getFields() {return fields;}
	public void setFields(PivotFields fields) {this.fields = fields;}
	
	@JsonProperty("fieldList")
	private List<PivotField> fieldList;
	public List<PivotField> getFieldList() {if(fieldList==null) {fieldList = new ArrayList<PivotField>();}return fieldList;}
	public void setFieldList(List<PivotField> fieldList) {this.fieldList = fieldList;}
}