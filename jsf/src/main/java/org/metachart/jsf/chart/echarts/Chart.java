package org.metachart.jsf.chart.echarts;

import java.io.IOException;
import java.util.UUID;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.PostAddToViewEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.chart.echarts.Chart")
@ListenerFor(systemEventClass=PostAddToViewEvent.class)
public class Chart extends UINamingContainer
{
	private String chartId = UUID.randomUUID().toString().replaceAll("-","");

	final static Logger logger = LoggerFactory.getLogger(Chart.class);

	public String getChartId() {return chartId;}
	public void setCodeId(String chartId) {this.chartId = chartId;}

 
	@Override
	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException
	{
		
		super.processEvent(event);
	}

 	@Override
 	public void encodeBegin(FacesContext ctx) throws IOException
 	{
 		super.encodeBegin(ctx);
	}



 	@Override
 	public void encodeEnd(FacesContext ctx) throws IOException
 	{
 		super.encodeEnd(ctx);

 	}
}