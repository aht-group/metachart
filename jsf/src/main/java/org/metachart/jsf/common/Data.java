package org.metachart.jsf.common;

import java.io.IOException;
import java.util.Objects;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import org.exlp.util.jx.ComponentAttribute;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.common.Data")
public class Data extends UINamingContainer implements org.metachart.interfaces.chart.Data
{
	final static Logger logger = LoggerFactory.getLogger(Data.class);
	
	private enum Attribute {type,value}
	public enum Type{data,category,edge}
	
	private String type; public void setType(String type) {this.type = type;} public String getType() {return type;}
	private JsonData value; @Override public JsonData getValue() {return value;} public void setData(JsonData value) {this.value = value;}

	public static Data instance(JsonData value) {Data data = new Data(); data.setData(value); return data;}
	public Data()
	{
		
	}
	
	@Override public void encodeAll(FacesContext ctx) throws IOException
	{
		if(Objects.isNull(type)) {type = ComponentAttribute.toString(ctx,this,Attribute.type,Type.data.toString());}
		value = ComponentAttribute.toObject(ctx,this, Attribute.value, null);
	}
}