package org.metachart.factory.json.chart.mc;

import java.io.IOException;
import java.io.Writer;

public class JsonMcGradientBarFactory
{	
	private final Writer writer;
		
	public static JsonMcGradientBarFactory instance(Writer writer) {return new JsonMcGradientBarFactory(writer);}
	private JsonMcGradientBarFactory(Writer writer)
	{
		this.writer=writer;
	}
	
	public String init(String divId) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n\nheatBar.init(document.getElementById('").append(divId).append("'));");
		writer.write(sb.toString());
		return sb.toString();
	}
	
	public String addRandomData() throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\nfor(i=0;i<16;i++)");
		sb.append("\n{");
		sb.append("\n  heatBar.drawGradient(mcRandomInt(0,300));");
		sb.append("\n}");
		writer.write(sb.toString());
		return sb.toString();
	}
}