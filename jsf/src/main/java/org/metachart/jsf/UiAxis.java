package org.metachart.jsf;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.xml.datatype.XMLGregorianCalendar;

import org.metachart.xml.chart.Data;
import org.metachart.xml.chart.Ds;

@FacesComponent(value="org.metachart.jsf.UiAxis")
public class UiAxis extends UINamingContainer
{
	public String categories(Ds ds)
	{
		StringBuffer sb = new StringBuffer();
		if(ds!=null && ds.getData().size()>0)
		{
            sb.append("categories: [");

			for(Data data : ds.getData())
			{
				if(data.getCategory()!=null) {sb.append("'").append(data.getCategory()).append("',");}
				if(data.getRecord()!=null) {appendDate(data.getRecord(),sb);}
			}
            sb.deleteCharAt(sb.length()-1);
            sb.append("],");
		}
		return sb.toString();
	}

	   private void appendDate(XMLGregorianCalendar xmlGc, StringBuffer sb)
	    {
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