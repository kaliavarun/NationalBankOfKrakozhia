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

  final long MAX_ACCOUNT_NUMBER_VALUE = 9999999999999999L;
  final long MIN_ACCOUNT_NUMBER_VALUE = 1000000000000000L;

  private final AccountRepository accountRepository;

  @Autowired
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account createAccount(@NonNull AccountDTO accountDTO) {
    // Can be replaced with factory pattern if this is a composite service
    // containing other services.
    Account account = new Account();
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

  public List<Account> findAccountsByCustomerId(@NonNull Long customerId) {
    return null;
  }

  public Account findAccountsByAccountNumber(@NonNull Long accountNumber) {
    return accountRepository.findByAccountNumber(accountNumber);
  }

  /**
   * To be generated using database in real
   *
   * @return Random account number
   */
  private Long accountNumberGenerator() {
    return Math.abs(
        Float.valueOf(
                new Random().nextFloat() * (MAX_ACCOUNT_NUMBER_VALUE - MIN_ACCOUNT_NUMBER_VALUE))
            .longValue());
  }
}
