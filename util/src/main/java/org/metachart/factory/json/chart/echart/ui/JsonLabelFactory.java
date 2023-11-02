package org.metachart.factory.json.chart.echart.ui;

import org.metachart.model.json.chart.echart.ui.JsonLabel;

public class JsonLabelFactory
{	
	private JsonLabel json;
	
	public static JsonLabelFactory instance() {return new JsonLabelFactory();}
	private JsonLabelFactory()
	{
		json = new JsonLabel();
	}
	
	public JsonLabel build() {return json;}
	
	public JsonLabelFactory colorGrey() {json.setColor("rgba(0,0,0,0.7)"); return this;}
	
	public JsonLabelFactory fontArial() {json.setFontFamily("Arial"); return this;}
	
	public JsonLabelFactory fontSize(Integer value) {json.setFontSize(value); return this;}
}