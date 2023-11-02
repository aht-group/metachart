package org.metachart.jsf.common;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import org.metachart.model.json.chart.echart.data.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.io.JsonUtil;

@FacesComponent(value="org.metachart.jsf.common.Data")
public class Data extends UINamingContainer implements org.metachart.interfaces.chart.Data
{
final static Logger logger = LoggerFactory.getLogger(Data.class);
	
	private static enum Attribute {value}
	
	private JsonData value; @Override public JsonData getValue() {return value;} public void setValue(JsonData value) {this.value = value;}
	
	@Override public String getFamily() {return "javax.faces.Output";}
	
	@Override
	public void encodeAll(FacesContext ctx) throws IOException
	{
		Map<String,Object> map = this.getAttributes();
		value    = (JsonData) map.get(Attribute.value.toString());
		
		if(Objects.nonNull(value))
		{
			JsonUtil.info(value);
		}
	}
}