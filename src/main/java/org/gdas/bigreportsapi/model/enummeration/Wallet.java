package org.gdas.bigreportsapi.model.enummeration;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Wallet {

    OPERATIONS("Operações"),
    COMMISSION("Comissão"),
    PROFIT("Lucro"),
    REVENUE("Receita"),
    BILLED("Faturado");

    private final String label;

    Wallet(String label) {
        this.label = label;
    }

    public String getValue() {
        return this.name();
    }

    public String getLabel() {
        return label;
    }
}
