package com.nbk.dao.repository;

import com.nbk.dao.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository<T extends Account> extends JpaRepository<T, Long> {}
