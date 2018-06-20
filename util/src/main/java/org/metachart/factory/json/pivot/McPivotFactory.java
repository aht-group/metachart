package org.metachart.factory.json.pivot;

import org.metachart.model.json.pivot.PivotContainer;
import org.metachart.model.json.pivot.PivotFieldList;
import org.metachart.model.json.pivot.PivotFields;
import org.metachart.model.json.pivot.PivotValue;

public class McPivotFactory
{
	protected PivotFields fields;
	protected PivotFieldList fieldList;
	
	public McPivotFactory()
	{
		this.clear();
	}
	
	public static PivotContainer build() {return new PivotContainer();}
	
	public void clear()
	{
		fields = new PivotFields();
		fieldList = new PivotFieldList();
	}
	
	public void rows(String... rows)
	{
		for(String row : rows)
		{
			fields.getRows().add(row);
		}
	}
	
	public void columns(String... columns)
	{
		for(String column : columns)
		{
			fields.getColumns().add(column);
		}
	}
	
	public void value(PivotValue.Method method, String id)
	{
		fields.getValues().add(PivotValueFactory.build(method, id));
	}
	
	public PivotContainer toContainer()
	{
		PivotContainer json = new PivotContainer();
		json.setFields(fields);
		json.setFieldList(fieldList);
		return json;
	}
}