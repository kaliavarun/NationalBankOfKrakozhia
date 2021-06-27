package com.nbk.dao.repository;

import com.nbk.dao.domain.account.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentAccountRepository
    extends JpaRepository<CurrentAccount, Long> {}
