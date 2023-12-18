package org.metachart.jsf.chart;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.xml.datatype.XMLGregorianCalendar;

import org.metachart.jsf.chart.high.Chart;
import org.metachart.xml.chart.Data;
import org.metachart.xml.chart.Ds;
import org.primefaces.util.ComponentTraversalUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@FacesComponent(value="org.metachart.jsf.chart.UiDataSet")
public class UiDataSet extends UINamingContainer
{
	final static Logger logger = LoggerFactory.getLogger(UiDataSet.class);
	private Map<String, Object> chartAttrs;
	public UiDataSet()
	{
		chartAttrs = new HashMap<>();
	}

/*	public String data(DataSet ds)
	{
		logger.info("Building data ");

		if(ds==null) {logger.warn("ds is NULL");}
		else {logger.warn("size: "+ds.getData().size());}

		StringBuffer sb = new StringBuffer();
		if(ds!=null && ds.getData().size()>0)
		{
			for(Data data : ds.getData())
			{
                if(data.isSetRecord()){appendDate(data.getRecord(),sb);}
				if(data.isSetY()){sb.append(data.getY());}
                else{sb.append("null");}
                if(data.isSetRecord()){sb.append("]");}
				sb.append(", ");
			}
			return sb.substring(0, sb.length()-2);
		}
		return sb.toString();
	}
*/
	@Override
 	public void encodeBegin(FacesContext ctx) throws IOException
 	{
		try
		{
			UIComponent parentChart = ComponentTraversalUtils.closest(Chart.class, this);
			chartAttrs = parentChart.getAttributes();
		}
		catch (Exception e) {logger.info("Did not found a parent component of type chart chart");
		}
		if(chartAttrs.containsKey("linkDataTableId")) {
			Map<String, Object> attrs = this.getAttributes();
			attrs.put("pointSelect","true");
			attrs.put("pointEvent","true");
			//logger.info("ADDED ATTRIBUTES: " +this.getId());
		}

		super.encodeBegin(ctx);
 	}

	public String data(Ds ds)
	{
		logger.info("Building data ");

		if(ds==null) {logger.warn("ds is NULL");}
		else {logger.warn("size: "+ds.getData().size());}

		StringBuffer sb = new StringBuffer();
		if(ds!=null && ds.getData().size()>0)
		{
			for(Data data : ds.getData())
			{
				if(Objects.nonNull(data.getRecord()) || Objects.nonNull(data.getX())){sb.append("[");}
				if(Objects.nonNull(data.getRecord()) && Objects.nonNull(data.getX())) {sb.append("x:"+data.getX()+" , "); sb.append("name:"); appendDate(data.getRecord(),sb);}
                
				if(Objects.nonNull(data.getRecord()) && Objects.isNull(data.getX())){appendDate(data.getRecord(),sb);}
                else if(Objects.nonNull(data.getX()) && Objects.isNull(data.getRecord())) {sb.append(data.getX()+" , ");}
                
                if(Objects.nonNull(data.getY()) && Objects.nonNull(data.getRecord()) && Objects.nonNull(data.getX())) {sb.append("y:"+data.getY());}
                else if(Objects.nonNull(data.getY())) {sb.append(data.getY());}
                else{sb.append("null");}
                
                if(Objects.nonNull(data.getRecord()) || Objects.nonNull(data.getX())){sb.append("]");}
				sb.append(", ");
				if(Objects.nonNull(data.getRecord()) && Objects.nonNull(data.getX())) {sb = new StringBuffer(sb.toString().replaceAll("\\[", "\\{").replaceAll("\\]", "\\}"));}
			}
			return sb.substring(0, sb.length()-2);
		}
		return sb.toString();
	}

    private void appendDate(XMLGregorianCalendar xmlGc, StringBuffer sb)
    {
//        [Date.UTC(2006,  0,  1), 0.5   ]
        sb.append("Date.UTC(");
        sb.append(xmlGc.getYear());
        sb.append(",").append(xmlGc.getMonth()-1);
        sb.append(",").append(xmlGc.getDay());
        sb.append(",").append(xmlGc.getHour());
        sb.append(",").append(xmlGc.getMinute());
        sb.append(",").append(xmlGc.getSecond());
        sb.append("), ");
    }
}