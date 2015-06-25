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
		
		String argument   = "";
		ArrayList<String> parameterList = new ArrayList<String>();
		if (parameters!=null)
		{
			parameterList.addAll(Arrays.asList(parameters.split("\\s*,\\s*")));
			for (String parameter : parameterList)
			{
				argument = argument + "'" +parameter +"',";
			}
			argument = argument.substring(0, argument.lastIndexOf(","));
		}
		
		String javascriptDefinition = "";
		if (type.equals(aggregatorType.COUNT.toString()))
			{
				javascriptDefinition += "'" +label +"' :      function() { return tpl.count()() },";
			}
		if (type.equals(aggregatorType.AVERAGE.toString()))
			{
				javascriptDefinition += "'" +label +"' :      function() { return tpl.average()(['"+parameters +"'])},";
			}
		if (type.equals(aggregatorType.SUM.toString()))
			{
				javascriptDefinition += "'" +label +"' :      function() { return tpl.sum(usFmt)(['"+parameters +"'])},";
			}
		if (type.equals(aggregatorType.INTSUM.toString()))
		{
			javascriptDefinition += "'" +label +"' :      function() { return tpl.sum(usFmtInt)(['"+parameters +"'])},";
		}
		if (type.equals(aggregatorType.MAX.toString()))
		{
			javascriptDefinition += "'" +label +"' :      function() { return tpl.max()(['"+parameters +"'])},";
		}
		if (type.equals(aggregatorType.MIN.toString()))
		{
			javascriptDefinition += "'" +label +"' :      function() { return tpl.min()(['"+parameters +"'])},";
		}
		logger.trace(javascriptDefinition);
		ctx.getResponseWriter().write("      " +javascriptDefinition);
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