package org.metachart.factory.json.chart.echart.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.exlp.util.io.JsonUtil;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonEdge;

public class JsonDataFactory
{
	public enum Type {data,category}
	
	private JsonData json;
	
	private List<LocalDateTime> times;
	private List<String> strings;
	private List<Double> doubles1;
	private List<double[]> doubles2;
	private List<List<JsonData>> areas;
	
	public static JsonDataFactory instance() {return new JsonDataFactory();}
	private JsonDataFactory()
	{
		json = JsonDataFactory.create();
	}
	
	@SuppressWarnings("unchecked")
	public JsonData assemble()
	{
		if(Objects.nonNull(times)) {json.setTimes(times.toArray(new LocalDateTime[times.size()]));}
		if(Objects.nonNull(strings)) {json.setStrings(strings.toArray(new String[strings.size()]));}
		if(Objects.nonNull(doubles1)) {json.setDoubles1(doubles1.stream().mapToDouble(Double::doubleValue).toArray());}
		if(Objects.nonNull(doubles2)) {json.setDoubles2(doubles2.stream().toArray(double[][]::new));}
		if(Objects.nonNull(areas)) {json.setAreas(areas.stream().toArray(List[]::new));}
		
		return json;
	}
	
	public JsonDataFactory id(String seriesId) {json.setId(seriesId); return this;}
	public JsonDataFactory type(Type type) {json.setMcType(type.toString()); return this;}
	
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
	public JsonDataFactory time(LocalDateTime ldt, double value)
	{
		if(Objects.isNull(times)) {times = new ArrayList<>();}
		if(Objects.isNull(doubles1)) {doubles1 = new ArrayList<>();}
		
		times.add(ldt);
		doubles1.add(value);
		return this;
	}
	
	public JsonDataFactory data(JsonData value)
	{
		if(Objects.isNull(json.getData())) {json.setData(new ArrayList<>());}
		json.getData().add(value);
		return this;
	}
	public JsonDataFactory edge(JsonEdge edge)
	{
		if(Objects.isNull(json.getEdges())) {json.setEdges(new ArrayList<>());}
		json.getEdges().add(edge);
		return this;
	}
	
	public JsonDataFactory axisRange(LocalDateTime from, LocalDateTime to)
	{
		List<JsonData> l = new ArrayList<>();
		JsonData d1 = new JsonData(); d1.setxAxis(from); l.add(d1);
		JsonData d2 = new JsonData(); d2.setxAxis(to); l.add(d2);
		
		if(Objects.isNull(areas)) {areas = new ArrayList<>();}
		areas.add(l);
		return this;
	}
	
	
	public JsonDataFactory name(String value) {json.setName(value); return this;}
	public JsonDataFactory category(int index) {json.setCategory(index); return this;}

	
	public static JsonData create() {return new JsonData();}
	public static JsonData build(String name)
	{
		JsonData json = JsonDataFactory.create();
		json.setName(name);
		return json;
	}
	
	public static JsonData random1Int(int size, int max)
	{
		Random rnd = new Random();
		JsonDataFactory jf = JsonDataFactory.instance();
		for(int i=0;i<size;i++) {jf.double1(rnd.nextInt(max));}
		return jf.assemble();
	}
}