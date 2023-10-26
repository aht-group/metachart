package org.metachart.factory.txt.chart.echarts;

public class TxtRandomDataFactory
{	
	public static String randomDataDate()
	{
		String date = "[now.getFullYear(), now.getMonth() + 1, now.getDate()].join('-')";
		String time = "[now.getHours(), now.getMinutes(), now.getSeconds()].join(':')";
		
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\nfunction randomData()");
		sb.append("\n{");
		sb.append("\n  now = new Date();");
		sb.append("\n  value = Math.random() * 10;");
		sb.append("\n  return {value: ["+date+"+' '+"+time+", Math.round(value)]};");
		sb.append("\n}");
		return sb.toString();
	}
	

}