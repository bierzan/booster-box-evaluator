package com.brzn.box_eval.mtg_io_client.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class BoosterDeserializer extends JsonDeserializer {
    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
//        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        TreeNode arrayNode = jsonParser.getCodec().createArrayNode();
        return arrayNode.toString();
    } //todo  sprawdzenie co zrobi z boostera
}
