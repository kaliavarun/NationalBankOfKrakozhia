package com.nbk.dao.repository;

import com.nbk.dao.domain.account.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
