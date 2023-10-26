package org.metachart.factory.txt.chart.echarts;

public class TxtEchartFunctioFactory
{	
	public static String pushRandomData(String chart, String random)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\nsetInterval(function ()");
		sb.append("\n{");
		sb.append("\n x = randomData();");
		sb.append("\n  console.log(x);");
		sb.append("\n  data.push(x);"); 
		sb.append("\n  myChart.setOption( {series: [{data: data}]} );");
		sb.append("\n}, 1000);");
		return sb.toString();
	}
	

}