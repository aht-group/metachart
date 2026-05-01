package org.metachart.model.json.chart.echart.color;

public class ColorStop
{
	public static ColorStop of(double offset, String color) {return new ColorStop(offset,color);}
	private ColorStop(double offset, String color)
	{
        this.offset = offset;
        this.color = color;
    }
	
    private double offset;
    public double getOffset() {return offset;}

    private String color;
    public String getColor() {return color;}
}
