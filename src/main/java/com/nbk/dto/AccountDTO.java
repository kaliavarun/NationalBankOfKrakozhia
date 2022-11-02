package com.nbk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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
