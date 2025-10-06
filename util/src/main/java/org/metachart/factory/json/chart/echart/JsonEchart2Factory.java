package org.metachart.factory.json.chart.echart;

import java.util.ArrayList;
import java.util.Objects;

import org.metachart.model.json.chart.echart.JsonEchart;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonData;

public class JsonEchart2Factory
{
	private JsonEchart json;
	
	public static JsonEchart2Factory instance() {return new JsonEchart2Factory();}
	private JsonEchart2Factory()
	{
		json = JsonEchart2Factory.build();
	}
	
	public JsonEchart2Factory option(JsonOption option) {json.setOption(option); return this;}
	public JsonEchart2Factory add(JsonData data) {if(Objects.isNull(json.getDatas())) {json.setDatas(new ArrayList<>());} json.getDatas().add(data); return this;} 
	
	public JsonEchart assemble() {return json;}
	
	public static JsonEchart build()
	{
		return new JsonEchart();
	}
}