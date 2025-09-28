package org.metachart.factory.json.chart.echart.ui;

import org.metachart.model.json.chart.echart.ui.JsonEmphasis;
import org.metachart.model.json.chart.echart.ui.JsonLineStyle;

public class JsonEmphasisFactory
{
	public enum Focus {adjacency}
	
	private JsonEmphasis json;

	public static JsonEmphasisFactory instance() {return new JsonEmphasisFactory();}
	private JsonEmphasisFactory()
	{
		json = new JsonEmphasis();
	}
	
	public JsonEmphasisFactory lineStyle(JsonLineStyle lineStyle) {json.setLineStyle(lineStyle); return this;}
	public JsonEmphasisFactory focus(Focus focus) {json.setFocus(focus.toString()); return this;}
	
	public JsonEmphasis assemble() {return json;}
}