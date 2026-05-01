package org.metachart.model.json.chart.echart.color;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ColorSerializer extends JsonSerializer<Color> {

    @Override
    public void serialize(Color value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value.isGradient()) {
            gen.writeStartArray();
            for (ColorStop stop : value.getGradientStops()) {
                gen.writeStartArray();
                gen.writeNumber(stop.getOffset());
                gen.writeString(stop.getColor());
                gen.writeEndArray();
            }
            gen.writeEndArray();
        }
        else {
            gen.writeString(value.getSingleColor());
        }
    }
}
