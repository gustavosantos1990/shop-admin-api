package org.gdas.bigreportsapi.model.enummeration;

public enum RequestStatus {

    CREATED("Criado"),
    READY("Cadastrado"),
    DOING("Em Produção"),
    DELIVERED("Entregue"),
    PROCESSED("Processado"),
    DONE("Finalizado");

    private final String label;

    RequestStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
