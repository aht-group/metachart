package org.metachart.model.json.chart.echart;

import java.io.Serializable;
import java.util.List;

import org.metachart.model.json.chart.echart.data.JsonData;

public class JsonEchart implements Serializable
{
	public static final long serialVersionUID=1;
	
	public enum Type {time,category,scatter,
						graph,sankey,gauge,
						heatbar,heatmap}
	
	private JsonOption option;
	public JsonOption getOption() {return option;}
	public void setOption(JsonOption option) {this.option = option;}
	
	private List<JsonData> datas;
	public List<JsonData> getDatas() {return datas;}
	public void setDatas(List<JsonData> datas) {this.datas = datas;}
}