package org.metachart.jsf.chart.echarts;

import java.util.UUID;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.PostAddToViewEvent;

import org.exlp.controller.handler.web.jsf.ExlpJsfHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.chart.echarts.Chart")
@ListenerFor(systemEventClass=PostAddToViewEvent.class)
public class Chart extends UINamingContainer
{
	final static Logger logger = LoggerFactory.getLogger(Chart.class);

	private String chartId; public String getChartId() {return chartId;}
	public void setCodeId(String chartId) {this.chartId = chartId;}

	public Chart()
	{
		chartId = UUID.randomUUID().toString().replaceAll("-","");
	}
 
	@Override
	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException
	{
		super.processEvent(event);
		ExlpJsfHandler.pushJsToHead(this.getFacesContext(), "echarts", "5.4.2/echarts.min.js");
	}
	
 
}