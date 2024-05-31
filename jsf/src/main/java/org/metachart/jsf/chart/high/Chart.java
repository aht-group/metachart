package org.metachart.jsf.chart.high;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.PostAddToViewEvent;

import org.primefaces.component.remotecommand.RemoteCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.chart.high.Chart")
@ListenerFor(systemEventClass=PostAddToViewEvent.class)
public class Chart extends UINamingContainer
{
	private String chartId = UUID.randomUUID().toString().replaceAll("-","");

	final static Logger logger = LoggerFactory.getLogger(Chart.class);

	public String getChartId() {return chartId;}
	public void setCodeId(String chartId) {this.chartId = chartId;}

    public void listener()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        MethodExpression ajaxEventListener = (MethodExpression) getAttributes().get("jsonSaveListener");
        ajaxEventListener.invoke(context.getELContext(), new Object[] {});
    }

	@Override
	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException
	{
		if(event instanceof PostAddToViewEvent)
		{
			UIOutput js = new UIOutput();
			js.setRendererType("javax.faces.resource.Script");
			js.getAttributes().put("library", "mcTypeScript");
			js.getAttributes().put("name", "dist/hc.bundle.js");
			FacesContext context = this.getFacesContext();
			context.getViewRoot().addComponentResource(context, js, "head");
		}
		super.processEvent(event);
	}

 	@Override
 	public void encodeBegin(FacesContext ctx) throws IOException
 	{
		UIViewRoot viewRoot = ctx.getViewRoot();
		 ExpressionFactory factory = ctx.getApplication().getExpressionFactory();
		logger.info("--- chart encodeBegin---");
 		//viewRoot.addComponentResource(context, componentResource);
 		UIComponent parentChart = this.getNamingContainer();
 		Map<String, Object> attribMap = parentChart.getAttributes();
 		/*
 		logger.info("--- chart attributes---");
 		for (Entry<String, Object> entry  : parentChart.getAttributes().entrySet()) {
			logger.info("key: " + entry.getKey() + "; value: " + entry.getValue());
		};
		*/
 		if(viewRoot.findComponent("selectPointForm") == null)
 		{
 			logger.info("--- creating selectPointCommand ---");
 			UIComponent selectPointForm = new HtmlForm();
 			selectPointForm.setId("selectPointForm");
 			RemoteCommand selectPointCommand = new RemoteCommand();
 			selectPointCommand.setId("selectPointCommand");
 			selectPointCommand.setName("selectGraphPoint");
 	        MethodExpression selectPointActionExpression = factory.createMethodExpression(ctx.getELContext(),"#{" + getAttributes().get("linkBackingBean")+  "." + getAttributes().get("chartAndTableHandler") + ".selectPoint}",null, new Class<?>[]{});
 	        selectPointCommand.setActionExpression(selectPointActionExpression);
 	        selectPointCommand.setUpdate((String) getAttributes().get("linkDataTableId"));
 	        selectPointForm.getChildren().add(selectPointCommand);
			viewRoot.addComponentResource(ctx, selectPointForm);
 		}

 		super.encodeBegin(ctx);
	}

 	@Override
 	public void encodeEnd(FacesContext ctx) throws IOException
 	{
 		super.encodeEnd(ctx);

 		UIViewRoot viewRoot = ctx.getViewRoot();
 		UIComponent selectPointForm = viewRoot.findComponent("selectPointForm");
 		selectPointForm.encodeAll(ctx);
 	}
}