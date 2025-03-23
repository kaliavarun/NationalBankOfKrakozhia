package com.nbk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    @NotNull
    private Long accountNumber;
    @NotNull
    private BigDecimal transactionAmount;
    @NotNull
    private String transactionType;
}
