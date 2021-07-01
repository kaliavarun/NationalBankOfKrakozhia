package com.nbk.dao.repository;

import com.nbk.dao.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a JOIN FETCH a.transactions where a.accountNumber = ?1")
    Account findByAccountNumber(Long accountNumber);
}
