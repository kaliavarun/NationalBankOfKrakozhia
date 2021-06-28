package com.nbk.dao.domain.account;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_ACCOUNT")
@Data
public class Account implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ACCOUNT_ID", nullable = false, unique = true)
  private Long accountId;

  @Column(name = "ACCOUNT_TYPE", nullable = false)
  private AccountTypeEnum accountType;

  @Column(name = "CUSTOMER_ID", nullable = false)
  private Long customerId;

  @Column(name = "ACCOUNT_NUMBER", nullable = false, unique = true)
  private Long accountNumber;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "TRANSACTION_ACCOUNT_ID")
  private List<Transaction> transactions = new ArrayList<Transaction>();
}
