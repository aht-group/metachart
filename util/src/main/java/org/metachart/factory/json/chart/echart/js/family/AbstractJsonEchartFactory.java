package org.metachart.factory.json.chart.echart.js.family;

import java.io.IOException;
import java.util.Objects;

import org.apache.commons.collections4.ListUtils;
import org.exlp.util.io.JsonUtil;
import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractJsonEchartFactory 
{
	final static Logger logger = LoggerFactory.getLogger(AbstractJsonEchartFactory.class);
	
	protected void let(JsonEchartFactory jfEchart, JsonDatas datas) throws IOException
	{
		for(JsonData d : ListUtils.emptyIfNull(datas.getList()))
		{
			if(Objects.nonNull(d.getMcType()))
			{
				switch(JsonDataFactory.Type.valueOf(d.getMcType()))
				{
					case category:  jfEchart.letCategory(d.getId()); break;
					case data:  jfEchart.letData(d.getId()); break;
					default: logger.warn("NYI");
				}
			}
			else
			{
				logger.warn("No type for "+JsonUtil.toStringSilent(d));
			}
		}
	}
}