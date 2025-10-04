package org.metachart.model.json.chart.echart;

import java.io.Serializable;

public class JsonEchart implements Serializable
{
	public static final long serialVersionUID=1;
	
	public enum Type {time,category,scatter,
		graph,sankey,gauge,
						heatbar,heatmap}
}