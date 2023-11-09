package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.gdas.bigreportsapi.model.enummeration.Measure;
import org.gdas.bigreportsapi.model.enummeration.RequestStatus;

import static org.springframework.beans.BeanUtils.copyProperties;

public class RequestStatusJSON {

    @JsonProperty
    private int order;

    @JsonProperty
    private String severity;

    @JsonProperty
    private String label;

    @JsonProperty
    @NotBlank
    private String value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Measure toEnum() {
        return Measure.valueOf(this.value);
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public static RequestStatusJSON from(RequestStatus source) {
        RequestStatusJSON target = new RequestStatusJSON();
        copyProperties(source, target);
        target.setValue(source.name());
        return target;
    }

}
