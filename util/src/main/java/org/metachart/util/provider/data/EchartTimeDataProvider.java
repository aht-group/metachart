package org.metachart.util.provider.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	
	public EchartTimeDataProvider data(Data data) {return this.data(data.getValue());}
	public EchartTimeDataProvider data(JsonData data)
	{
		if(Objects.isNull(datas)) {datas = new ArrayList<>();}
		datas.add(data);
		return this;
	}
	
	
}