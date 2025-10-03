package org.metachart.factory.json.chart.echart.grid;

import java.util.List;

import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.grid.JsonMarkArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonMarkAreaFactory
{
	final static Logger logger = LoggerFactory.getLogger(JsonMarkAreaFactory.class);
	
	private JsonMarkArea json;
	
	public static JsonMarkAreaFactory instance()
	{
		return new JsonMarkAreaFactory();
	}
	private JsonMarkAreaFactory()
	{
		json = JsonMarkAreaFactory.build();
	}
	
	public JsonMarkAreaFactory clear() {json=JsonMarkAreaFactory.build() ; return this;}

//	public JsonMarkAreaFactory data(boolean magic, String id, String name) {json.setData(magic ? JsUtil.magicField(TxtDataFactory.dataId(id,name)) : TxtDataFactory.id(id,name)); return this;}
	public JsonMarkAreaFactory data(JsonData data) {json.setData(data.getAreas()); return this;}
	
	public JsonMarkArea assemble() {return json;}
	
	public static JsonMarkArea build() {return new JsonMarkArea();}

}