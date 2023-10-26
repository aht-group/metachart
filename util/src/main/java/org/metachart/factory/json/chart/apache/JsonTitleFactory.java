package org.metachart.factory.json.chart.apache;

import org.metachart.model.json.chart.apache.JsonTitle;

public class JsonTitleFactory
{	
	public static JsonTitle build() {return new JsonTitle();}
	public static JsonTitle build(String text)
	{
		JsonTitle json = JsonTitleFactory.build();
		json.setText(text);
		return json;
	}
}