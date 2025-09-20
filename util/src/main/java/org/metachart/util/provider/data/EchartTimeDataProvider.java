package org.metachart.util.provider.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.metachart.interfaces.chart.Data;
import org.metachart.model.json.chart.echart.data.JsonData;

public class EchartTimeDataProvider
{
	private List<JsonData> datas; public List<JsonData> getDatas() {return datas;}
	
	public static EchartTimeDataProvider instance()
	{
		EchartTimeDataProvider p = new EchartTimeDataProvider();
		
		return p;
	}
	
	private EchartTimeDataProvider()
	{
		
	}
	
	public EchartTimeDataProvider datas(List<Data> datas)
	{
		for(Data d : ListUtils.emptyIfNull(datas)) {this.data(d);}
		return this;
	}
	public EchartTimeDataProvider data(Data data) {return this.data(data.getValue());}
	
	public EchartTimeDataProvider datas(JsonData[] datas)
	{
		if(ObjectUtils.isNotEmpty(datas)) {for(JsonData data : datas) {this.data(data);}}
		return this;
	}
	public EchartTimeDataProvider data(JsonData data)
	{
		if(Objects.isNull(datas)) {datas = new ArrayList<>();}
		datas.add(data);
		return this;
	}
	
	
}