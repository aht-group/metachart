package org.metachart.jsf.common;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import org.exlp.util.jx.ComponentAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.common.Title")
public class Title extends UINamingContainer implements org.metachart.interfaces.chart.Title
{
final static Logger logger = LoggerFactory.getLogger(Title.class);
	
	private static enum Attribute {name,value}
	
	private String value; @Override public String getValue() {return value;} public void setValue(String value) {this.value = value;}
	
	@Override public String getFamily() {return "javax.faces.Output";}
	
	@Override
	public void encodeAll(FacesContext ctx) throws IOException
	{
		logger.info("encodeAll");
		value = ComponentAttribute.toString(ctx,this,Attribute.value);
	}
}