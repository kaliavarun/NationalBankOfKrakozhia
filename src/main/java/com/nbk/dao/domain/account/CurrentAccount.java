package com.nbk.dao.domain.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "T_CURRENT_ACCOUNT")
public class CurrentAccount extends Account {

  @Column(name = "ACCOUNT_YEAR", nullable = false)
  private Integer currentAccountYear;
}
