package org.gdas.bigreportsapi.model.enummeration;

public enum RequestStatus {

    CREATED(0, "warning", "Criado"),
    READY(1, "info", "Cadastrado"),
    DOING(2, "info", "Em Produção"),
    DELIVERED(3, "info", "Entregue"),
    PROCESSED(4, "info", "Processado"),
    DONE(5, "success", "Finalizado"),
    CANCELED(6, "danger", "Cancelado");

    private final int order;
    private final String severity;
    private final String label;

    RequestStatus(int order, String severity, String label) {
        this.order = order;
        this.severity = severity;
        this.label = label;
    }

    public int getOrder() {
        return order;
    }

    public String getSeverity() {
        return severity;
    }

    public String getLabel() {
        return label;
    }
}
