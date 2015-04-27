package org.metachart.jsf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.PivotAggregator")
public class PivotAggregator extends UINamingContainer
{
final static Logger logger = LoggerFactory.getLogger(PivotAggregator.class);
	
	private static enum Attribute {type, parameters}
	
	private enum aggregatorType {SUM, AVERAGE, COUNT};
	private String type;
	private String parameters;
	
	@Override
	public void encodeAll(FacesContext ctx) throws IOException
	{
		Map<String,Object> map = this.getAttributes();
		this.type         = (String) map.get(Attribute.type.toString());
		this.parameters   = (String) map.get(Attribute.parameters.toString());
		
		String argument   = "";
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.addAll(Arrays.asList(parameters.split("\\s*,\\s*")));
		for (String parameter : parameterList)
		{
			argument = argument + "'" +parameter +"',";
		}
		argument = argument.substring(0, argument.lastIndexOf(","));
		String javascriptDefinition = "aggregators: {";
		if (type.equals(aggregatorType.COUNT.toString()))
			{
				javascriptDefinition += "'Number of' :      function() { return tpl.count()() },";
			}
		if (type.equals(aggregatorType.AVERAGE.toString()))
			{
				javascriptDefinition += "'Average' :      function() { return tpl.average()(['"+parameters +"'])},";
			}
		if (type.equals(aggregatorType.SUM.toString()))
			{
				javascriptDefinition += "'Sum' :      function() { return tpl.sum()(['"+parameters +"'])},";
			}
		javascriptDefinition += "},";
		logger.info(javascriptDefinition);
		ctx.getResponseWriter().write(javascriptDefinition);
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