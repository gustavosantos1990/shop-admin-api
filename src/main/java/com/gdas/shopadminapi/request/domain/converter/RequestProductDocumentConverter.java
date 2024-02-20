package com.gdas.shopadminapi.request.domain.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdas.shopadminapi.request.domain.RequestProductDocument;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Converter
public class RequestProductDocumentConverter implements AttributeConverter<RequestProductDocument, String> {

    private final ObjectMapper mapper;

    public RequestProductDocumentConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String convertToDatabaseColumn(RequestProductDocument requestProductDocument) {
        if (requestProductDocument == null) return null;
        try {
            return mapper.writeValueAsString(requestProductDocument);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("current value can't be converted to JSON", e);
        }
    }

    @Override
    public RequestProductDocument convertToEntityAttribute(String string) {
        if (isEmpty(string)) return null;
        try {
            return mapper.readValue(string, RequestProductDocument.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("current value can't be converted to POJO", e);
        }
    }
}
