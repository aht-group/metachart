package org.metachart.factory.json.graph.mc;

import java.util.ArrayList;
import java.util.Objects;

import org.metachart.model.json.graph.mc.JsonCategory;
import org.metachart.model.json.graph.mc.JsonEdge;
import org.metachart.model.json.graph.mc.JsonGraph;
import org.metachart.model.json.graph.mc.JsonNode;

public class JsonGraphFactory
{	
	private JsonGraph json;
	
	public static JsonGraphFactory instance() {return new JsonGraphFactory();}
	private JsonGraphFactory()
	{
		json = JsonGraphFactory.create();
	}
	
	public JsonGraphFactory node(JsonNode node) {if(Objects.isNull(json.getNodes())) {json.setNodes(new ArrayList<>());} json.getNodes().add(node); return this;}
	public JsonGraphFactory edge(JsonEdge edge) {if(Objects.isNull(json.getEdges())) {json.setEdges(new ArrayList<>());} json.getEdges().add(edge); return this;}
	public JsonGraphFactory category(JsonCategory category) {if(Objects.isNull(json.getCategories())) {json.setCategories(new ArrayList<>());} json.getCategories().add(category); return this;}
	
	public JsonGraph build()
	{
		return json;
	}
	
	public static JsonGraph create() {return new JsonGraph();}
}