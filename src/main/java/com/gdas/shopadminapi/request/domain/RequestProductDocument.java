package com.gdas.shopadminapi.request.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gdas.shopadminapi.product.domain.enumeration.Measure;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestProductDocument implements Serializable {

    private String name;
    private List<RequestProductComponent> components;

    public RequestProductDocument() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RequestProductComponent> getComponents() {
        return components;
    }

    public void setComponents(List<RequestProductComponent> components) {
        this.components = components;
    }
}
