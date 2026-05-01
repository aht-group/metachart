package org.metachart.model.json.chart.echart.color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

public class ColorDeserializer extends JsonDeserializer<Color>
{

    private static final Logger logger = LoggerFactory.getLogger(ColorDeserializer.class);

    @Override
    public Color deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);

        if (node.isTextual()) {
            return Color.of(node.asText());
        }

        if (node.isArray()) {
            List<ColorStop> stops = new ArrayList<>();
            for (JsonNode stopNode : node) {
                double offset = stopNode.get(0).asDouble();
                String hex = stopNode.get(1).asText();
                stops.add(ColorStop.of(offset, hex));
            }
            return Color.of(stops);
        }

        logger.warn("Unsupported color JSON node: {}", node);
        throw new JsonMappingException(parser, "Unsupported color format");
    }
}
