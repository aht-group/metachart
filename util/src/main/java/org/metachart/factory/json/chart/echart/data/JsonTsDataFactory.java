package org.metachart.factory.json.chart.echart.data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonTsDataFactory
{	
//	private JsonTsData json;
	private List<Object[]> tsDataItems;
	
	public static JsonTsDataFactory instance() {return new JsonTsDataFactory();}
	private JsonTsDataFactory()
	{
//		json = JsonTsDataFactory.create();
	}
	
//	public JsonTsData build()
//	{
//		if(Objects.nonNull(tsDataItems)) {json.setValue(tsDataItems);}
//		return json;
//	}
//
//	public static JsonTsData create() {return new JsonTsData();}

	public void addTsData(LocalDateTime plusHours, double value) 
	{
		if(Objects.isNull(tsDataItems)) {tsDataItems = new ArrayList<>();}
		Object[] tsDataItem = new Object[2];
		tsDataItem[0] = plusHours.toInstant(ZoneOffset.UTC).toEpochMilli();
		tsDataItem[1] = value;
		tsDataItems.add(tsDataItem);
	}
	
}