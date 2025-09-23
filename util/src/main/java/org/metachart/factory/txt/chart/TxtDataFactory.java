package org.metachart.factory.txt.chart;

import java.util.Objects;

import org.metachart.model.json.chart.echart.data.JsonData;

public class TxtDataFactory
{
	public static String dataId(String chartId, String seriesId)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("data");
		sb.append(TxtDataFactory.id(chartId, seriesId));
		return sb.toString();
	}
	public static String id(String chartId, String seriesId)
	{
		StringBuilder sb = new StringBuilder();
		if(Objects.nonNull(seriesId)) {sb.append(seriesId);}
		sb.append(chartId);
		
		return sb.toString();
	}
	
	public static String double1ToInteger1(JsonData data)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for(double d : data.getDoubles1())
		{
			int i = (int)d;
			sb.append(i).append(" ");
		}
		sb.append("]");
		return sb.toString();
	}
	
}