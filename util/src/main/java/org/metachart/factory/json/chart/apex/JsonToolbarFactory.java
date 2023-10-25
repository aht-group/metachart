package org.metachart.factory.json.chart.apex;

import org.metachart.model.json.chart.apex.JsonToolbar;

public class JsonToolbarFactory
{	
	public static JsonToolbar build() {return new JsonToolbar();}
	public static JsonToolbar build(boolean show)
	{
		JsonToolbar json = JsonToolbarFactory.build();
		json.setShow(show);
		return json;
	}
}