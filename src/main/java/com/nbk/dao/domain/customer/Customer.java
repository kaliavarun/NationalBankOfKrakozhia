package com.nbk.dao.domain.customer;

import com.nbk.dao.domain.account.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_CUSTOMER")
@Data
@NoArgsConstructor
public class Customer implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CUSTOMER_ID", nullable = false, unique = true)
  private long id;

  @Column(name = "CUSTOMER_NAME", nullable = false)
  @NonNull
  private String name;

  @Column(name = "CUSTOMER_SURNAME", nullable = false)
  @NonNull
  private String surname;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "CUSTOMER_ID")
  private Set<Account> accounts = new HashSet<Account>();
}
