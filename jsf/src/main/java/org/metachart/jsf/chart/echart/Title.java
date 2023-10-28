package org.metachart.jsf.chart.echart;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import org.exlp.util.jsf.jx.ComponentAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.chart.echart.Title")
public class Title extends UINamingContainer implements org.metachart.interfaces.chart.Title
{
	final static Logger logger = LoggerFactory.getLogger(Title.class);
	
	private static enum Attribute {name}
	
	private String name; @Override public String getName() {return name;} public void setName(String name) {this.name = name;}
	
	@Override
	public void encodeAll(FacesContext ctx) throws IOException
	{
		logger.info("encodeAll");
		name = ComponentAttribute.toString(ctx,this,Attribute.name);	
	}
	
	@Override public String getFamily() {return null;}
}