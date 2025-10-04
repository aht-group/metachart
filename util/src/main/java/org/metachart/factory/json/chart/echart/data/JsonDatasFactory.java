package org.metachart.factory.json.chart.echart.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.ListUtils;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonDatas;

public class JsonDatasFactory
{	
	private JsonDatas json;
	
	public static JsonDatasFactory instance() {return new JsonDatasFactory();}
	private JsonDatasFactory()
	{
		json = JsonDatasFactory.build();
	}
	
	public JsonDatasFactory add(JsonData data) {if(Objects.isNull(json.getList())){json.setList(new ArrayList<>());} json.getList().add(data); return this;}
	public JsonDatasFactory add(List<JsonData> list) {if(Objects.isNull(json.getList())){json.setList(new ArrayList<>());} json.getList().addAll(ListUtils.emptyIfNull(list)); return this;}
	
	public JsonDatas assemble() {return json;}
	
	public static JsonDatas build() {return new JsonDatas();}
	public static JsonDatas build(JsonData[] array)
	{
		JsonDatas json = JsonDatasFactory.build();
		json.setList(Arrays.asList(array));
		return json;
	}
	public static JsonDatas build(List<JsonData> list)
	{
		JsonDatas json = JsonDatasFactory.build();
		json.setList(list);
		return json;
	}
}