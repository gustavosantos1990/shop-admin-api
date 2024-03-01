package com.gdas.shopadminapi.request.domain.enummeration;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RequestStatus {

    ESTIMATE(0, "warning", "Orçamento"),
    ACTIVE(1, "info", "Ativo"),
    DELIVERED(2, "info", "Entregue"),
    READY_TO_BILLING(3, "info", "Pronto para faturamento"),
    UNDER_BILLING(4, "info", "Sob faturamento"),
    BILLING_RESULTS(5, "info", "Faturando resultados"),
    BILLED(6, "info", "Faturado"),
    DONE(7, "success", "Finalizado"),
    CANCELED(8, "danger", "Cancelado");

    private final int sequence;
    private final String severity;
    private final String label;

    RequestStatus(int sequence, String severity, String label) {
        this.sequence = sequence;
        this.severity = severity;
        this.label = label;
    }

    public String getValue() {
        return this.name();
    }

    public int getSequence() {
        return sequence;
    }

    public String getSeverity() {
        return severity;
    }

    public String getLabel() {
        return label;
    }
}
