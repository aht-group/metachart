package org.metachart.factory.txt.chart;

import org.metachart.model.json.chart.echart.data.JsonData;

public class TxtDataFactory
{	
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