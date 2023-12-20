package org.gdas.bigreportsapi.model.enummeration;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FinancialMovementOperation {

    ADVANCE_OR_PAYMENT("Adiantamento/Pagamento de pedido"),
    WITHDRAWAL_OR_DELIVERY("Retirada/Entrega de pedido"),
    EXPENSE_REIMBURSEMENT("Reembolso de despesas de pedido"),
    COST_REIMBURSEMENT("Reembolso de custos de produção"),
    OPERATION_COST("Custos de operação"),
    COMMISSION_OF_REQUEST("Comissão de resultado de venda"),
    PROFIT("Lucro"),
    REQUEST_PROCESSING("Faturamento de pedido"),
    COMMISSION_PAYMENT("Pagamento de comissão"),
    ACQUISITION("Compra de material"),
    PROFIT_WITHDRAW("Retirada de lucro");

    private final String label;

    FinancialMovementOperation(String label) {
        this.label = label;
    }

    public String getValue() {
        return this.name();
    }

    public String getLabel() {
        return label;
    }
}
