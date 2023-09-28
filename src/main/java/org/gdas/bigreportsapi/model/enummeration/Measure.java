package org.gdas.bigreportsapi.model.enummeration;

public enum Measure {

    UNITY("Unidade", "unit."),
    MT("Metro", "mt"),
    CM("Centímetro", "cm"),
    MT2("Metro Quadrado", "mt²"),
    CM2("Centímetro Quadrado", "cm²");

    private String label;
    private String symbol;

    Measure(String label, String symbol) {
        this.label = label;
        this.symbol = symbol;
    }
}
