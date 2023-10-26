package org.gdas.bigreportsapi.model.enummeration;

public enum BillingItemType {

    EXPENSE("Despesa"),
    REVENUE("Receita"),
    PRODUCTION_COST("Custo de Produção");

    private final String text;

    BillingItemType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
