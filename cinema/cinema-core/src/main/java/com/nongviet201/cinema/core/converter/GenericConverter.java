package com.nongviet201.cinema.core.converter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.List;

@Converter(autoApply = true)
public class GenericConverter<T extends Enum<T>> implements AttributeConverter<List<T>, String> {

    private final Class<T> clazz;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public GenericConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String convertToDatabaseColumn(List<T> enumList) {
        try {
            return objectMapper.writeValueAsString(enumList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert list to JSON", e);
        }
    }

    @Override
    public List<T> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON to list", e);
        }
    }
}