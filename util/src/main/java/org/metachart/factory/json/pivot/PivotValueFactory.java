package org.metachart.factory.json.pivot;

import org.metachart.model.json.pivot.PivotValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PivotValueFactory
{
	final static Logger logger = LoggerFactory.getLogger(PivotValueFactory.class);
	
	public static PivotValue build(PivotValue.Method method, String id)
	{
		PivotValue json = new PivotValue();
		json.setId(id);
		json.setMethod(method.toString());
		return json;
	}
}