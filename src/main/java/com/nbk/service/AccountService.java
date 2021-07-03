package com.nbk.service;

import com.nbk.dao.domain.account.Account;
import com.nbk.dao.domain.account.AccountTypeEnum;
import com.nbk.dao.repository.AccountRepository;
import com.nbk.dto.AccountDTO;
import com.nbk.exception.NationalBankOfKrakozhiaException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class AccountService {

  private static final long ACCOUNT_NUMBER_RANGE = 9999999999999999L - 1000000000000000L;

  private final AccountRepository accountRepository;

  @Autowired
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account createAccount(@NonNull AccountDTO accountDTO) {
    // Can be replaced with factory pattern if this is a composite service
    // containing other services.
    var account = new Account();
    account.setCustomerId(accountDTO.getCustomerId());
    account.setAccountNumber(accountNumberGenerator());

    switch (accountDTO.getAccountType()) {
      case "CURRENT":
        account.setAccountType(AccountTypeEnum.CURRENT);
        break;
      case "SAVINGS":
        account.setAccountType(AccountTypeEnum.SAVINGS);
        break;
      default:
        throw new NationalBankOfKrakozhiaException("Invalid Account Type");
    }

    return accountRepository.save(account);
  }

  public Account findAccountsByAccountNumber(@NonNull Long accountNumber) {
    return accountRepository.findByAccountNumber(accountNumber);
  }

  /**
   * In real to be generated based on factors like location, branch, time etc
   *
   * @return Random account number
   */
  private Long accountNumberGenerator() {
    return Float.valueOf(new Random().nextFloat() * ACCOUNT_NUMBER_RANGE).longValue();
  }
}
