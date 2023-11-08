package org.metachart.factory.json.chart.echart.data;

import java.util.ArrayList;
import java.util.Objects;

import org.metachart.model.json.chart.echart.data.JsonEdge;

public class JsonEdgeFactory
{	
	private JsonEdge json;
	
	public static JsonEdgeFactory instance() {return new JsonEdgeFactory();}
	private JsonEdgeFactory()
	{
		json = JsonEdgeFactory.create();
	}
	
	public JsonEdge build() {return json;}
	
	public JsonEdgeFactory edge(JsonEdge edge)
	{
		if(Objects.isNull(json.getEdges())) {json.setEdges(new ArrayList<>());}
		json.getEdges().add(edge);
		return this;
	}
	
	public static JsonEdge create() {return new JsonEdge();}
	
	public static JsonEdge edge(int source, int target)
	{
		JsonEdge e = JsonEdgeFactory.create();
		e.setSource(source);
		e.setTarget(target);
		return e;
	}
}