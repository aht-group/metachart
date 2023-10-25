package org.metachart.factory.json.chart.apex;

import org.metachart.model.json.chart.apex.JsonTitle;

public class JsonTitleFactory
{	
	public static JsonTitle build() {return new JsonTitle();}
	public static JsonTitle build(String text, boolean show)
	{
		JsonTitle json = JsonTitleFactory.build();
		json.setText(text);
		json.setShow(show);
		return json;
	}
}