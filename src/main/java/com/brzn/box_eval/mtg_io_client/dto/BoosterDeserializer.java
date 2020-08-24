package com.brzn.box_eval.mtg_io_client.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class BoosterDeserializer extends JsonDeserializer {
    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode arrayNode = jsonParser.readValueAsTree();
        String[][] result = new String[arrayNode.size()][];

        int i = 0;
        for (JsonNode node : arrayNode) {
            if (node.isArray()) {
                result[i] = new String[node.size()];
                int j = 0;
                for (JsonNode n : node) {
                    result[i][j] = n.textValue();
                    j++;
                }
            } else {
                result[i] = new String[] {node.textValue()};
            }
            i++;
        }
        return result;
    }
}
