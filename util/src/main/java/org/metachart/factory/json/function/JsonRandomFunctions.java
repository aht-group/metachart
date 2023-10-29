package org.metachart.factory.json.function;

import java.io.IOException;
import java.io.Writer;

public class JsonRandomFunctions
{
	private final Writer writer;
	
	public static JsonRandomFunctions instance(Writer writer) {return new JsonRandomFunctions(writer);}
	private JsonRandomFunctions(Writer writer)
	{
		this.writer=writer;
	}
	
	public String randomInt() throws IOException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("\nfunction mcRandomInt(min, max)");
		sb.append("\n{");
		sb.append("\n  return Math.floor(Math.random() * (max - min + 1)) + min;");
		sb.append("\n}");
		writer.write(sb.toString());
		return sb.toString();
	}
}