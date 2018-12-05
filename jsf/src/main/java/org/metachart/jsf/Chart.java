package org.metachart.jsf;

import java.util.UUID;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;

@FacesComponent(value="org.metachart.jsf.Chart")
public class Chart extends UINamingContainer
{
	
	private String chartId = UUID.randomUUID().toString().replaceAll("-","");
	
	public String getChartId() {return chartId;}
	public void setCodeId(String chartId) {this.chartId = chartId;}
}