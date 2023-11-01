package org.metachart.factory.json.function;

import java.io.IOException;
import java.io.Writer;

public class TxtEchartFunctionFactory
{
	private Writer writer; public TxtEchartFunctionFactory writer(Writer writer) {this.writer=writer; return this;}
	
	public static TxtEchartFunctionFactory instance() {return new TxtEchartFunctionFactory();}
	private TxtEchartFunctionFactory()
	{

	}
	
	public String pushRandomData(String chart, String random) throws IOException
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
		writer.write(sb.toString());
		return sb.toString();
	}
	
	public static String nullify(int dimension) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append(".map(function (item) {return [");
		for(int i=0;i<dimension;i++)
		{
			sb.append("item[").append(i).append("]");
			if(i<(dimension-1)) {sb.append(", ");}
		}
		sb.append(" || '-'];})");
		return sb.toString();
	}
}