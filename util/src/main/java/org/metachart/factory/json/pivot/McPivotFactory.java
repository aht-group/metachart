package org.metachart.factory.json.pivot;

import org.metachart.model.json.pivot.PivotField;
import org.metachart.model.json.pivot.PivotFields;
import org.metachart.model.json.pivot.PivotSettings;
import org.metachart.model.json.pivot.PivotValue;

public class McPivotFactory
{
	protected PivotSettings container;
	
	public McPivotFactory()
	{
		this.clear();
	}
	
	public static PivotSettings build() {return new PivotSettings();}
	
	public void clear()
	{
		container = new PivotSettings();
		container.setFields(new PivotFields());
	}
	
	public void rows(String... rows)
	{
		for(String row : rows)
		{
			container.getFields().getRows().add(row);
		}
	}
	
	public void columns(String... columns)
	{
		for(String column : columns)
		{
			container.getFields().getColumns().add(column);
		}
	}
	
	public void value(PivotValue.Method method, String id)
	{
		container.getFields().getValues().add(PivotValueFactory.build(method, id));
	}
	
	public void addField(String id, String label)
	{
		PivotField json = new PivotField();
		json.setId(id);
		json.setLabel(label);		
		container.getFieldList().add(json);
	}
	
	public PivotSettings toContainer()
	{
		return container;
	}
}