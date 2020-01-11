package org.metachart.jsf.map;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;

import org.metachart.xml.chart.Data;
import org.metachart.xml.chart.Ds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.map.UiDataSet")
public class UiDataSet extends UINamingContainer
{
	final static Logger logger = LoggerFactory.getLogger(UiDataSet.class);
	
	public String data(Ds ds)
	{
		logger.info("Building data ");
		
		if(ds==null) {logger.warn("ds is NULL");}
		
		StringBuffer sb = new StringBuffer();
		if(ds!=null && ds.getData().size()>0)
		{
			for(Data data : ds.getData())
			{
				sb.append("{");
				sb.append("value: ").append(data.getY());
				sb.append(", code: '").append(data.getCategory()).append("'");
				if(data.isSetLabel()) {sb.append(", label:'").append(data.getLabel()).append("'");}
				sb.append("},");
			}
			String result = sb.substring(0, sb.length()-1);
			logger.info(result);
			return result;
		}
		return sb.toString();
	}
}