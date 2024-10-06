package org.sample.doping.configuration;

import com.couchbase.client.java.codec.JsonSerializer;
import com.couchbase.client.java.codec.TypeRef;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;

public class CouchbaseJsonSerializer implements JsonSerializer {

    private final ObjectMapper objectMapper;

    public CouchbaseJsonSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public byte[] serialize(Object input) {
        String json;
        try {
            json = objectMapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public <T> T deserialize(Class<T> target, byte[] input) {
        var str = new String(input, StandardCharsets.UTF_8);
        try {
            return objectMapper.readerFor(target).readValue(str);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(TypeRef<T> target, byte[] input) {
        var str = new String(input, StandardCharsets.UTF_8);
        var javaType = objectMapper.getTypeFactory().constructType(target.type());
        try {
            return objectMapper.readValue(str, javaType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
