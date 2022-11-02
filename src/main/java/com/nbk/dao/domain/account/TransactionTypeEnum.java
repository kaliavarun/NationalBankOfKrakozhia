package com.nbk.dao.domain.account;

public enum TransactionTypeEnum {
    CREDIT(1L),
    DEBIT(2L);

    private final Long code;

    TransactionTypeEnum(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }
}
