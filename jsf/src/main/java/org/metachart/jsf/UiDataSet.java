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
		for(Data data : ds.getData())
		{
			sb.append(data.getY()).append(", ");
		}
		return sb.substring(0, sb.length()-2);
	}
}