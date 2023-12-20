package org.gdas.bigreportsapi.model.enummeration;

public enum BillingItemType {

    REVENUE("Receita"),
    EXPENSE("Despesa"),
    OPERATIONS("Operações"),
    COMMISSION("Comissão"),
    PROFIT("Lucro");

    private final String text;

    BillingItemType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
