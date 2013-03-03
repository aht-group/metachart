package org.metachart.jsf;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;

import org.metachart.xml.Data;
import org.metachart.xml.DataSet;

@FacesComponent(value="org.metachart.jsf.UiDataSet")
public class UiDataSet extends UINamingContainer
{
	public String data(DataSet ds)
	{
		StringBuffer sb = new StringBuffer();
		if(ds!=null)
		{
			for(Data data : ds.getData())
			{
//				[Date.UTC(2006,  0,  1), 0.5   ]
				sb.append("[Date.UTC(");
				sb.append(data.getRecord().getYear());
				sb.append(",").append(data.getRecord().getMonth()-1);
				sb.append(",").append(data.getRecord().getDay());
				sb.append("), ");
				sb.append(data.getY()).append("]");
				sb.append(", ");
			}
			return sb.substring(0, sb.length()-2);
		}
		return sb.toString();
	}
}