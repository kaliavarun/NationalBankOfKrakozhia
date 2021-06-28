package com.nbk.dao.domain.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "T_TRANSACTION")
public class Transaction implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TRANSACTION_ID", nullable = false)
  private Long transactionId;

  @Column(name = "TRANSACTION_ACCOUNT_ID", nullable = false)
  private Long transactionAccountId;

  @Column(name = "TRANSACTION_TYPE", nullable = false)
  private TransactionTypeEnum transactionType;

  @Column(name = "TRANSACTION_AMMOUNT", nullable = false)
  private BigDecimal transactionAmount;
}
