package org.metachart.factory.json.chart.echart.grid;

import java.util.Objects;

import org.metachart.interfaces.chart.Title;
import org.metachart.model.json.chart.echart.grid.JsonTitle;

public class JsonTitleFactory
{	
	public static JsonTitle build() {return new JsonTitle();}
	public static JsonTitle build(String text)
	{
		JsonTitle json = JsonTitleFactory.build();
		json.setText(text);
		return json;
	}
	
	public static JsonTitle build(Title title)
	{
		JsonTitle json = JsonTitleFactory.build();
		if(Objects.nonNull(title))
		{
			json.setText(title.getValue());
		}
		return json;
	}
}