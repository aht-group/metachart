package org.metachart.interfaces.chart;

import java.io.IOException;

import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.metachart.model.json.chart.echart.grid.JsonGrid;

public interface EchartJsFactory
{
	void json(JsonGrid grid, JsonDatas datas, JsonOption option) throws IOException;
}