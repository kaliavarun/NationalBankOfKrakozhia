package com.nbk.dao.domain.account;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_ACCOUNT_TYPE")
@Data
public class AccountType implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ACCOUNT_TYPE_ID", nullable = false, unique = true)
  private Long accountTypeId;

  @Column(name = "ACCOUNT_TYPE", nullable = false, unique = true)
  private String accountType;
}
