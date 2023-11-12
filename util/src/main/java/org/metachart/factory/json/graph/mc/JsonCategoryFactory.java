package org.metachart.factory.json.graph.mc;

import org.metachart.model.json.graph.mc.JsonCategory;

public class JsonCategoryFactory
{	
	private JsonCategory json;
	
	public static JsonCategoryFactory instance() {return new JsonCategoryFactory();}
	private JsonCategoryFactory()
	{
		json = JsonCategoryFactory.create();
	}
	
	public JsonCategoryFactory id(long id) {json.setId(id); return this;}
	public JsonCategoryFactory label(String label) {json.setLabel(label); return this;}
	public JsonCategory build() {return json;}
	
	public static JsonCategory create() {return new JsonCategory();}
	public static JsonCategory createId(long id) {JsonCategory json = JsonCategoryFactory.create(); json.setId(id); return json;}
//	public static JsonCategory label(String label) {JsonCategory json = JsonCategoryFactory.create(); json.setLabel(label); return json;}
}