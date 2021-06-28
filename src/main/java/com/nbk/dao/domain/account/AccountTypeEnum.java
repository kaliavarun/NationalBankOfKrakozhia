package com.nbk.dao.domain.account;

public enum AccountTypeEnum {
  CURRENT(1L),
  SAVINGS(2L);

  private final Long code;

  AccountTypeEnum(Long code) {
    this.code = code;
  }

  public Long getCode() {
    return code;
  }
}
