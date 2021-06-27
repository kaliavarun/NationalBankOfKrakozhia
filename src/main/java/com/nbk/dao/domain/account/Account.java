package com.nbk.dao.domain.account;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "CUSTOMER_ID")
@Data
public abstract class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ACCOUNT_ID", nullable = false, unique = true)
  private Long accountId;

  @Column(name = "CUSTOMER_ID", nullable = false)
  private Long customerId;

  @Column(name = "ACCOUNT_NUMBER", nullable = false, unique = true)
  private Long accountNumber;

  @Column(name = "ACCOUNT_BALANCE", nullable = false)
  private BigDecimal accountBalance;
}
