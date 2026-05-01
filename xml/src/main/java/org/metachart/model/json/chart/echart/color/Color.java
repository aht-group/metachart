package org.metachart.model.json.chart.echart.color;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ColorSerializer.class)
@JsonDeserialize(using = ColorDeserializer.class)
public class Color {

    private String singleColor;

    private List<ColorStop> gradientStops;

    public static Color of(String hex) {
        Color color = new Color();
        color.singleColor = hex;
        return color;
    }

    public static Color of(List<ColorStop> stops) {
        Color color = new Color();
        color.gradientStops = stops;
        return color;
    }

    public boolean isGradient() {
        return gradientStops != null;
    }

    public String getSingleColor() {
        return singleColor;
    }

    public List<ColorStop> getGradientStops() {
        return gradientStops;
    }
}
