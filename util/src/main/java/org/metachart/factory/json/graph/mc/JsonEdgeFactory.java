package org.metachart.factory.json.graph.mc;

import org.metachart.model.json.graph.mc.JsonEdge;

public class JsonEdgeFactory
{	

	public static JsonEdge create() {return new JsonEdge();}
	
	public static JsonEdge id(long source, long destination)
	{
		JsonEdge json = JsonEdgeFactory.create();
		json.setSource(JsonNodeFactory.id(source));
		json.setDestination(JsonNodeFactory.id(destination));
		return json;
	}
}