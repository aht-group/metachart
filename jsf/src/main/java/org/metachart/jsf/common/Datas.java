package org.metachart.jsf.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.exlp.util.jx.ComponentAttribute;
import org.metachart.factory.json.chart.echart.data.JsonDatasFactory;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.common.Datas")
public class Datas extends UINamingContainer implements org.metachart.interfaces.chart.Datas
{
	final static Logger logger = LoggerFactory.getLogger(Datas.class);
	
	private enum Attribute {value}
	
	private List<JsonData> value; @Override public List<JsonData> getValue() {return value;} public void setValue(List<JsonData> value) {this.value = value;}
	
	@Override public void encodeAll(FacesContext ctx) throws IOException
	{
		value = ComponentAttribute.toObjects(ctx,this,Attribute.value);
	}
}