package com.gdas.shopadminapi.request.domain.enummeration;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RequestStatus {

    ACTIVE(0, "warning", "Ativo"),
    DELIVERED(3, "info", "Entregue"),
    READY_TO_BILLING(4, "info", "Pronto para faturamento"),
    UNDER_BILLING(4, "info", "Sob faturamento"),
    BILLING_RESULTS(5, "info", "Faturando resultados"),
    BILLED(6, "info", "Faturado"),
    DONE(7, "success", "Finalizado"),
    CANCELED(8, "danger", "Cancelado");

    private final int order;
    private final String severity;
    private final String label;

    RequestStatus(int order, String severity, String label) {
        this.order = order;
        this.severity = severity;
        this.label = label;
    }

    public String getValue() {
        return this.name();
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
