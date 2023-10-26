package org.gdas.bigreportsapi.model.enummeration;

public enum ResultType {

    OPERATION("Operações"),
    COMMISSION("Comissão"),
    PROFIT("Lucro");

    private final String text;

    ResultType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
