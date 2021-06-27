package com.nbk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
  private Long accountNumber;

  @NonNull private Long customerId;
  private BigDecimal initialCredit;

  @NonNull private String accountType;
}
