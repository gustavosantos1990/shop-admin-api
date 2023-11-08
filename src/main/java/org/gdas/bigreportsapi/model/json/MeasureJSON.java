package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.gdas.bigreportsapi.model.enummeration.Measure;

import static org.springframework.beans.BeanUtils.copyProperties;

public class MeasureJSON {

    @JsonProperty
    private String name;

    @JsonProperty
    @NotBlank
    private String code;

    @JsonProperty
    private String symbol;

    @JsonProperty("multi_dimension")
    private boolean multiDimension;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isMultiDimension() {
        return multiDimension;
    }

    public void setMultiDimension(boolean multiDimension) {
        this.multiDimension = multiDimension;
    }

    public Measure toEnum() {
        return Measure.valueOf(this.code);
    }

    public static MeasureJSON from(Measure source) {
        MeasureJSON target = new MeasureJSON();
        copyProperties(source, target);
        target.setCode(source.name());
        target.setName(source.getLabel());
        return target;
    }

}
