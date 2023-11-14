package org.metachart.factory.json.graph.mc;

import org.metachart.model.json.graph.mc.JsonNode;

public class JsonNodeFactory
{
	public static JsonNode create() {return new JsonNode();}
	
	public static JsonNode id(long id) {JsonNode json = JsonNodeFactory.create(); json.setId(id); return json;}
	public static JsonNode label(String label) {JsonNode json = JsonNodeFactory.create(); json.setLabel(label); return json;}
}