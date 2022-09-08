package com.sadesoft.portal.base.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {
    private final ObjectMapper mapper;

    public JsonMapper() {
        this.mapper = new ObjectMapper();
    }

    public <T> T jsonToObject(JsonNode node, Class<T> c) {
        try {
            return mapper.treeToValue(node, c);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode objectToJson(Object o) {
        return mapper.valueToTree(o);
    }

    public JsonNode stringToJson(String s){
        try {
            return mapper.readTree(s);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
