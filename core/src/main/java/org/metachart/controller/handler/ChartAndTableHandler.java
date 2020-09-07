package org.metachart.controller.handler;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import javax.el.ExpressionFactory;
import javax.faces.context.FacesContext;
import javax.faces.event.BehaviorEvent;

import org.metachart.interfaces.EntityWithRecord;
import org.primefaces.PrimeFaces;
import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.behavior.ajax.AjaxBehaviorListenerImpl;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChartAndTableHandler <TSDATA extends EntityWithRecord>
{
	final static Logger logger = LoggerFactory.getLogger(ChartAndTableHandler.class);
	
	private List<TSDATA> tsDataTableDatas; public List<TSDATA> getTsDataTableDatas() {return tsDataTableDatas;}
	
	private TSDATA selectedTsDatafromChart;
	public TSDATA getSelectedTsDatafromChart() {return selectedTsDatafromChart;}
	public void setSelectedTsDatafromChart(TSDATA selectedTsDatafromChart) {this.selectedTsDatafromChart = selectedTsDatafromChart;}
	
	private String widgetVar;
	private String handlerName;
	private int selectedPointIndex;
	
	public ChartAndTableHandler(String beanName)
	{
		this(beanName,"chartAndTableHandler");
	}
	
	public ChartAndTableHandler(String beanName, String handlerName)
	{
		this.dataTable = new DataTable();
		this.beanName = beanName;
		this.handlerName = handlerName;

		Random random = new Random();
		widgetVar = "widgetVar" + random.nextInt(25);

		initDataTable(this.dataTable);
	}


	

//------Binding and Initialise data table Begin-----------
	private Object tableState;
	private String beanName;
	private DataTable dataTable;
	/**
	 * Binding data table to bean
	 * Since binding is data table in session does not restore state of object properly
	 * we need to restore state every time when user refresh the pages otherwise we will get
	 * duplicate id problems
	 */
	public DataTable getDataTable() {
		logger.info("Initialise of new Data table on refresh.....");
		this.dataTable = new DataTable();
		if (tableState != null) {
			logger.info("restore state of Data table.....");
			this.dataTable.restoreState(FacesContext.getCurrentInstance(), tableState);
		}
		initDataTable(this.dataTable);
		return this.dataTable;
	}

	public void setDataTable(DataTable dataTable) {
		logger.info("backup data table state on refresh");
		tableState = dataTable.saveState(FacesContext.getCurrentInstance());
	}


	private void initDataTable(DataTable dataTable)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		ExpressionFactory ef = fc.getApplication().getExpressionFactory();

		//Set settings for data table
		dataTable.setSelectionMode("single");
		dataTable.setWidgetVar(widgetVar);
		javax.el.MethodExpression rowSelectMethodExpression = ef.createMethodExpression(fc.getELContext(), "#{" +beanName +"." + handlerName + ".selectTsDataRow}", null, new Class<?>[]{BehaviorEvent.class});
		AjaxBehavior ajaxBehavior = (AjaxBehavior) fc.getApplication().createBehavior(AjaxBehavior.BEHAVIOR_ID);
		ajaxBehavior.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(rowSelectMethodExpression, rowSelectMethodExpression));
		dataTable.addClientBehavior("rowSelect", ajaxBehavior);

		//Set selected selection and values
		//dataTable.setValue(this.getTsDataTableDatas());
		dataTable.setSelection(selectedTsDatafromChart);
	}

	public void updateDataTable(List<TSDATA> tsDatas)
	{
		this.tsDataTableDatas = tsDatas;
		//Set Data
		//this.dataTable.setValue(this.getTsDataTableDatas());
	}
//------Binding and Initialise data table Begin-----------

//-------------Row Selection Begin -----------------------------------------------
/**
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * Row selection
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * Data table row select even will ajax call the  selectTsDataRow
 *
 * selectTsDataRow will delegate the the responsibility of
 * finding the correct time value to selectRowExecute.
 * it can be overridden in extending class but responsibility of selectRowExecute
 * should stay the same
 *
 * selectRowExecute will in turn find correct time from record field and execute the
 * corresponding java script defined in chart component
 *
 */
	@SuppressWarnings("unchecked")
	public void selectTsDataRow(SelectEvent event)
	{
		selectedTsDatafromChart =(TSDATA) event.getObject();
		logger.info("Selected Timeseries Data Entity with record:" +selectedTsDatafromChart.getRecord().getTime());
		selectRowExecute(selectedTsDatafromChart);
	}

	@SuppressWarnings("deprecation")
	protected <T extends EntityWithRecord> void selectRowExecute(T ejb) {
		logger.info(ejb.getRecord().toString());
		logger.info("milliseconds time:" + ejb.getRecord().getTime());
		logger.info("timezone offset:" + ejb.getRecord().getTimezoneOffset());
		logger.info("milliseconds:" + ejb.getRecord().toInstant().toEpochMilli());
		long timeInRecord = ejb.getRecord().toInstant().toEpochMilli() - ejb.getRecord().getTimezoneOffset()*60*1000;
		logger.info("timeInRecord:" +timeInRecord);
		PrimeFaces.current().executeScript("selectElementInChart(" + timeInRecord + ");");
		//RequestContext requestContext = RequestContext.getCurrentInstance();
		//requestContext.execute("selectElementInChart(" + timeInRecord + ");");
		//RequestContext.getCurrentInstance().execute("selectElementInChart(" + ratingPoint.getRecord().toInstant().toEpochMilli() + ");");
	}

//-------------Row Selection End -----------------------------------------------

//-------------Point selection form a chart Begins --------------------------------
	/**
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * Point selection form a chart
	 * Selecting a point in chart will fire ajax call to selectPoint with
	 * Json parameters
	 *
	 * Among the sent parameter time value in Json is an
	 * important parameters time is set as utc timestamp and parameters are
	 * fetched from faces context request parameters.
	 *
	 * selectPoint will delegate the
	 * responsibility of
	 * finding correct time series data from the list to getSelectedPoint, and
	 * setting data table selection and jumping to correct page  to setDataTableSelection
	 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */
	public void selectPoint() throws ParseException
	{
		logger.info("Ajax call select point");
		logger.info("data table size:" + tsDataTableDatas.size());
		selectedTsDatafromChart = this.getSelectedPoint(tsDataTableDatas);
		logger.info("-->selected point with redord: " + selectedTsDatafromChart.getRecord().toString());
		logger.info("setting PageDataTable");
		setDataTableSelection(selectedPointIndex);
	}

	protected TSDATA getSelectedPoint(List<TSDATA> pointsWithRecords)
	{
		int index;
		selectedPointIndex = 0;
		String time = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("time");
		logger.info("time:" + time);
		Date selectedDate = Date.from(Instant.ofEpochMilli(Long.parseLong(time)));
		logger.info(selectedDate.toString());
		ListIterator<TSDATA> it = pointsWithRecords.listIterator();
		while (it.hasNext())
		{
			index = it.nextIndex();
			Object o = it.next();
			logger.info(o.getClass().getSimpleName()+" "+o.toString());
			TSDATA tempPoint = (TSDATA)o;
			//logger.info("index : "  + index +" date : " +tempPoint.getRecord().toString());
			//if(DateUtils.isSameInstant(tempRatingPoint.getRecord(), selectedDate)){
			if(selectedDate.compareTo(tempPoint.getRecord())==0)
			{
				selectedPointIndex=index;
				logger.info("Returning Point:" + tempPoint.getRecord() );
				return tempPoint;
			}
		}
		logger.info("Returning Point NULL");
		return null;
	}

	protected void setDataTableSelection(int index)
	{
		this.dataTable.setSelection(selectedTsDatafromChart);
		//logger.info("----> index: " + index + " -->Record: " + selectedTsDatafromChart.getRecord().toString());
		//logger.info("----> Id:" + dataTable.getId());
		//logger.info("----> Client id:" + dataTable.getClientId());
		//logger.info("--> getContainerClientId : " + dataTable.getContainerClientId(FacesContext.getCurrentInstance()));

		//PrimeFaces.current().ajax().update(getDataTableId());
		logger.info(dataTable.getClientId());
		//PrimeFaces.current().ajax().update(dataTable.getClientId());

	    int pageSize = dataTable.getRowsToRender();
	    //logger.info("page size:" + pageSize);
	    //logger.info("widget var:" + dataTable.getWidgetVar());
	    if(pageSize < 1) {pageSize = 1;}
	    int pageNo = (int)Math.ceil(index*1d/pageSize);
	    if((index % pageSize)> 0) { pageNo = pageNo -1;}
	    if(pageNo < 0) {
	    	pageNo = 0;
	    }

	    //logger.info("index :" + index*1d);
	    //logger.info("page size:" + pageSize);
	    //logger.info("page no: " + pageNo);
	    PrimeFaces.current().executeScript("PF('" + dataTable.getWidgetVar() +"').paginator.setPage(" + pageNo + ");");
	    //--PrimeFaces.current().executeScript("PF('" + dataTable.getWidgetVar() +"').paginator.setPage(" + pageNo + ");");
	    //RequestContext.getCurrentInstance().execute("PF('" + dataTableWV +"').paginator.setPage(" + pageNo + ");");
	}
	//-------------Point selection form a chart Ends --------------------------------
}
