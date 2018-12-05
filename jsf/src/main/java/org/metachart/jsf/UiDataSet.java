package org.metachart.jsf;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.xml.datatype.XMLGregorianCalendar;

import org.metachart.xml.chart.Data;
import org.metachart.xml.chart.Ds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.UiDataSet")
public class UiDataSet extends UINamingContainer
{
	final static Logger logger = LoggerFactory.getLogger(UiDataSet.class);
	
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

    private void appendDate(XMLGregorianCalendar xmlGc, StringBuffer sb)
    {
//        [Date.UTC(2006,  0,  1), 0.5   ]
        sb.append("[Date.UTC(");
        sb.append(xmlGc.getYear());
        sb.append(",").append(xmlGc.getMonth()-1);
        sb.append(",").append(xmlGc.getDay());
        sb.append(",").append(xmlGc.getHour());
        sb.append(",").append(xmlGc.getMinute());
        sb.append(",").append(xmlGc.getSecond());
        sb.append("), ");
    }
}