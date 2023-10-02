package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.gdas.bigreportsapi.model.enummeration.Measure;

public class MeasureJSON {

    @JsonProperty
    private String label;

    @JsonProperty
    @NotBlank
    private String value;

    @JsonProperty
    private String symbol;

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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static MeasureJSON from(Measure measure) {
        MeasureJSON json = new MeasureJSON();
        json.setLabel(measure.getLabel());
        json.setSymbol(measure.getSymbol());
        json.setValue(measure.name());
        return json;
    }

}
