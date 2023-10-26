package org.gdas.bigreportsapi.model.enummeration;

public enum EventType {

    INCOME("Entrada (+)"),
    DEBT("Saída (-)");

    private final String label;

    EventType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
