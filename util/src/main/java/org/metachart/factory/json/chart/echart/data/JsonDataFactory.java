package org.metachart.factory.json.chart.echart.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.metachart.model.json.chart.echart.data.JsonData;

public class JsonDataFactory
{	
	private JsonData json;
	private List<String> strings;
	private List<Double> doubles1;
	private List<double[]> doubles2;
	
	public static JsonDataFactory instance() {return new JsonDataFactory();}
	private JsonDataFactory()
	{
		json = JsonDataFactory.create();
	}
	
	public JsonData build()
	{
		if(Objects.nonNull(strings)) {json.setStrings(strings.toArray(new String[strings.size()]));}
		if(Objects.nonNull(doubles1)) {json.setDoubles1(doubles1.stream().mapToDouble(Double::doubleValue).toArray());}
		if(Objects.nonNull(doubles2)) {json.setDoubles2(doubles2.stream().toArray(double[][]::new));}
		return json;
	}
	
	public JsonDataFactory repeat(int number)
	{
		if(Objects.isNull(strings)) {strings = new ArrayList<>();}
		for(int i=0;i<number;i++) {strings.add(""+i);}
		return this;
	}
	public JsonDataFactory string(String value)
	{
		if(Objects.isNull(strings)) {strings = new ArrayList<>();}
		strings.add(value);
		return this;
	}
	public JsonDataFactory double1(double value)
	{
		if(Objects.isNull(doubles1)) {doubles1 = new ArrayList<>();}
		doubles1.add(value);
		return this;
	}
	public JsonDataFactory double2(double[] value)
	{
		if(Objects.isNull(doubles2)) {doubles2 = new ArrayList<>();}
		doubles2.add(value);
		return this;
	}
	
	public static JsonData create() {return new JsonData();}
	
	public static JsonData build(String name)
	{
		JsonData json = JsonDataFactory.create();
		json.setName(name);
		return json;
	}
	
//	public static JsonData d
}