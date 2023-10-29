package org.metachart.factory.json.function;

import java.io.IOException;
import java.io.Writer;

public class TxtRandomDataFactory
{	
	private Writer writer; public TxtRandomDataFactory writer(Writer writer) {this.writer=writer; return this;}
	
	public static TxtRandomDataFactory instance() {return new TxtRandomDataFactory();}
	private TxtRandomDataFactory()
	{

	}
	
	public String randomDataDate() throws IOException
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
		writer.write(sb.toString());
		return sb.toString();
	}
	

}