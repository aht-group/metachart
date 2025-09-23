package org.metachart.factory.json.chart.echart.data;

import java.util.List;

import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonDatas;

public class JsonDatasFactory
{	
	public static JsonDatas build() {return new JsonDatas();}
	public static JsonDatas build(List<JsonData> list)
	{
		JsonDatas json = JsonDatasFactory.build();
		json.setList(list);
		return json;
	}
}