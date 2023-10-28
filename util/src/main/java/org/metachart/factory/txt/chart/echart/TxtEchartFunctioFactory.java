package org.metachart.factory.txt.chart.echart;

import java.io.IOException;
import java.io.Writer;

public class TxtEchartFunctioFactory
{
	private Writer writer; public TxtEchartFunctioFactory writer(Writer writer) {this.writer=writer; return this;}
	
	public static TxtEchartFunctioFactory instance() {return new TxtEchartFunctioFactory();}
	private TxtEchartFunctioFactory()
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
	

}