package org.metachart.factory.json.chart.echart.ui;

import org.apache.commons.collections4.ListUtils;
import org.exlp.util.io.JsUtil;
import org.metachart.factory.txt.chart.TxtDataFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonSeries;

public class JsonOptionFactory
{	
	public static JsonOption toMagicDatas(String id, JsonOption option)
	{	
		for(JsonSeries s : ListUtils.emptyIfNull(option.getSeries()))
		{
			s.setData(JsUtil.magicField(TxtDataFactory.dataId(id,s.getData())));
		}
		return option;
	}
}