package org.gdas.bigreportsapi.model.enummeration;

import static org.gdas.bigreportsapi.model.enummeration.EventType.*;

public enum EventReason {

    REQUEST_PAYMENT("Pagamento de pedido", INCOME),
    ACQUISITION("Compra", DEBT),
    ADDITIONAL_PRODUCTION_COST("Custo de produção adicional", DEBT),
    PROFIT_WITHDRAWAL("Saque de Lucro", DEBT),
    COMMISSION_PAYMENT("Pagamento de Comissão", DEBT),
    DELIVERY("Entrega/Retirada", DEBT),
    UNKNOWN("Avulso", null);

    private final String label;
    private final EventType type;

    EventReason(String label, EventType type) {
        this.label = label;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public EventType getType() {
        return type;
    }
}
