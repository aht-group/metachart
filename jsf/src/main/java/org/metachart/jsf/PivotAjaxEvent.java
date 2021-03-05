package org.metachart.jsf;

import java.util.Arrays;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;
import javax.faces.event.FacesListener;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This type of event is used in combination with the &lt;f:ajax&gt; tag.
 * Because the listener attribute of a JSF 2 - AJAX tag requires an
 * instance of an AjaxBehaviorEvent, further class casting is needed
 * to work with the custom features
 */
public class PivotAjaxEvent extends AjaxBehaviorEvent
{
	final static Logger logger = LoggerFactory.getLogger(PivotAjaxEvent.class);
	private static final long serialVersionUID = 1L;

	private final static String keyRows = "org.metachart.savePivot.rows";
	private final static String keyCols = "org.metachart.savePivot.cols";
	private List<String> rows; public List<String> getRows() {return rows;} public void setRows(List<String> rows) {this.rows = rows;}

	private List<String> cols; public List<String> getCols() {return cols;} public void setCols(List<String> cols) {this.cols = cols;}


	public PivotAjaxEvent(UIComponent component, ClientBehavior behavior)
	{
		super(component, behavior);
	}


	public void setRowAndColumn(java.util.Map<String,String> params)
	{
		MapUtils.debugPrint(System.out, "params as String", params);

		if(params.containsKey(keyRows) && params.containsKey(keyCols))
		{
			String strRows = params.get(keyRows);
			String strCols = params.get(keyCols);
			setRows(Arrays.asList(StringUtils.splitPreserveAllTokens(strRows, ",")));
	        setCols(Arrays.asList(StringUtils.splitPreserveAllTokens(strCols, ",")));
		}
	}


	@Override public boolean isAppropriateListener(FacesListener faceslistener) {return (faceslistener instanceof AjaxBehaviorListener);}
    @Override public void processListener(FacesListener faceslistener) {((AjaxBehaviorListener) faceslistener).processAjaxBehavior(this);}
}