package com.nbk.dao.repository;

import com.nbk.dao.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Query("SELECT c FROM Customer c JOIN FETCH c.accounts where c.id = ?1")
  Optional<Customer> findById(Long id);
}