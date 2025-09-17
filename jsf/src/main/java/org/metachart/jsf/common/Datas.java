package org.metachart.jsf.common;

import java.io.IOException;
import java.util.List;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import org.exlp.util.jx.ComponentAttribute;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.common.Datas")
public class Datas extends UINamingContainer implements org.metachart.interfaces.chart.Datas
{
	final static Logger logger = LoggerFactory.getLogger(Datas.class);
	
	private enum Attribute {value}
	
	private List<JsonData> value; @Override public List<JsonData> getValue() {return value;} public void setValue(List<JsonData> value) {this.value = value;}
	
	@Override public void encodeAll(FacesContext ctx) throws IOException
	{
		value = ComponentAttribute.toObjects(ctx,this,Attribute.value);
	}
}