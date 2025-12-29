package org.metachart.factory.json.chart.orgchart.js.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonDatasFactory;
import org.metachart.factory.json.chart.echart.grid.JsonAxisFactory;
import org.metachart.factory.json.chart.echart.ui.JsonOptionFactory;
import org.metachart.factory.txt.chart.TxtDataFactory;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.metachart.model.json.chart.orgchart.data.JsonData;
import org.metachart.model.json.chart.orgchart.data.JsonOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrgchartDemo
{
	final static Logger logger = LoggerFactory.getLogger(OrgchartDemo.class);
	
	public static void demo(JsonEchartFactory jfEchart) throws IOException
	{
//		jfEchart.letCategory("X").letData("A");
//		jfEchart.category("X",OrgchartDemo.toCategory("X"));
//		jfEchart.dataDoubles1(OrgchartDemo.toData("A"));
//		jfEchart.option(JsonOptionFactory.toMagicDatas(OrgchartDemo.toOption()));
	}
	
	public static JsonOptions toOptions() 
	{
		JsonOptions options = new JsonOptions();
		options.setNodeContent("title");
		options.setData(toData());
		
		return options;
	}
	
	public static JsonData toData()
	{
		JsonData data = new JsonData();
		data.setName("Lorem");
		data.setTitle("1. Level");
		data.setRelationship("001");
		data.setChildren(new ArrayList<JsonData>());
		
		JsonData child = new JsonData();
		child.setName("Ipsum");
		child.setTitle("2. Level");
		child.setRelationship("110");
		child.setChildren(new ArrayList<JsonData>());
		
		data.getChildren().add(child);
		
		child = new JsonData();
		child.setName("Dolor");
		child.setTitle("2. Level");
		child.setRelationship("111");
		child.setChildren(new ArrayList<JsonData>());
		
		data.getChildren().add(child);
		
		JsonData grandchild = new JsonData();
		grandchild.setName("Sit");
		grandchild.setTitle("3. Level");
		grandchild.setRelationship("110");
		grandchild.setChildren(new ArrayList<JsonData>());
		
		child.getChildren().add(grandchild);
		
		grandchild = new JsonData();
		grandchild.setName("Amet");
		grandchild.setTitle("3. Level");
		grandchild.setRelationship("110");
		grandchild.setChildren(new ArrayList<JsonData>());
		
		child.getChildren().add(grandchild);
		
		return data;
	}
}