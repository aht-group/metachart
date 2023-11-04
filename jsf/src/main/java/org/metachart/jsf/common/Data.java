package org.metachart.jsf.common;

import java.io.IOException;

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
	
	private static enum Attribute {value}
	
	private JsonData value; 
	@Override public JsonData getValue() {return value;}
	public void setData(JsonData value) {this.value = value;}

	@Override public void encodeAll(FacesContext ctx) throws IOException
	{
		value = ComponentAttribute.toObject(ctx,this, Attribute.value, null);
	}
}