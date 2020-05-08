package org.metachart.jsf;

import java.util.UUID;

import javax.el.MethodExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

@FacesComponent(value="org.metachart.jsf.Chart")
public class Chart extends UINamingContainer
{

	private String chartId = UUID.randomUUID().toString().replaceAll("-","");

	public String getChartId() {return chartId;}
	public void setCodeId(String chartId) {this.chartId = chartId;}

    public void listener()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        MethodExpression ajaxEventListener = (MethodExpression) getAttributes().get("jsonSaveListener");
        ajaxEventListener.invoke(context.getELContext(), new Object[] {});
    }
}