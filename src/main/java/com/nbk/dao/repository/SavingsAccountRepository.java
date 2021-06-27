package com.nbk.dao.repository;

import com.nbk.dao.domain.account.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository
    extends JpaRepository<SavingsAccount, Long> {}
