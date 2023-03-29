package com.nbk.dto;

import jakarta.validation.constraints.NotNull;import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Long accountNumber;

    @NotNull
    private Long customerId;
    @NotNull
    private String accountType;
    private BigDecimal initialCredit;
}
