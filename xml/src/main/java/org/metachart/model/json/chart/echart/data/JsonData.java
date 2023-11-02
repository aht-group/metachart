package org.metachart.model.json.chart.echart.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonData implements Serializable
{
	public static final long serialVersionUID=1;
	
	@JsonProperty("name")
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	@JsonProperty("strings")
	private String[] strings;
	public String[] getStrings() {return strings;}
	public void setStrings(String[] strings) {this.strings = strings;}

	@JsonProperty("doubles1")
	private double[] doubles1;
	public double[] getDoubles1() {return doubles1;}
	public void setDoubles1(double[] doubles1) {this.doubles1 = doubles1;}
	
	@JsonProperty("doubles2")
	private double[][] doubles2;
	public double[][] getDoubles2() {return doubles2;}
	public void setDoubles2(double[][] doubles2) {this.doubles2 = doubles2;}
}