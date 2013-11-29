package org.metachart.jsf;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;

import org.metachart.xml.Data;
import org.metachart.xml.DataSet;

@FacesComponent(value="org.metachart.jsf.UiAxis")
public class UiAxis extends UINamingContainer
{
	public String categories(DataSet ds)
	{
		StringBuffer sb = new StringBuffer();
		if(ds!=null && ds.getData().size()>0)
		{
            sb.append("categories: [");

			for(Data data : ds.getData())
			{
				sb.append("'").append(data.getCategory()).append("',");
			}
            sb.deleteCharAt(sb.length()-1);
            sb.append("],");
		}
		return sb.toString();
	}
}