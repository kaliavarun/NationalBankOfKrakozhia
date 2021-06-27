package com.nbk.dao.domain.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "T_CUSTOMER")
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
}
