package org.metachart.jsf;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.PivotRenderer")
public class PivotRenderer extends UINamingContainer
{
final static Logger logger = LoggerFactory.getLogger(PivotRenderer.class);
	
	private static enum Attribute {type, parameters, label}
	
	private enum aggregatorType {SUM, AVERAGE, COUNT, MIN, MAX, INTSUM};
	private String type;
	private String parameters;
	private String label = "";
	
	@Override
	public void encodeAll(FacesContext ctx) throws IOException
	{
		Map<String,Object> map = this.getAttributes();
		this.type         = (String) map.get(Attribute.type.toString());
		this.parameters   = (String) map.get(Attribute.parameters.toString());
		if (map.get(Attribute.label.toString())!=null)
		{
			this.label    = (String) map.get(Attribute.label.toString());
		}
		else
		{
			this.label    = this.type;
		}
		
		
	}
	
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}

	public String getParameters() {return parameters;}
	public void setParameters(String parameters) {this.parameters = parameters;}

	@Override
	public String getFamily() {
		return null;
	}
}