package com.nbk.dao.domain.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "T_SAVINGS_ACCOUNT")
public class SavingsAccount extends Account {}
