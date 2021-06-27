package com.nbk.service;

import com.nbk.dao.domain.account.Account;
import com.nbk.dao.domain.account.CurrentAccount;
import com.nbk.dao.domain.account.SavingsAccount;
import com.nbk.dao.repository.CurrentAccountRepository;
import com.nbk.dao.repository.SavingsAccountRepository;
import com.nbk.dto.AccountDTO;
import com.nbk.exception.NationalBankOfKrakozhiaException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class AccountService {

  final long MAX_ACCOUNT_NUMBER_VALUE = 9999999999999999L;
  final long MIN_ACCOUNT_NUMBER_VALUE = 1000000000000000L;

  private final SavingsAccountRepository savingsAccountRepository;
  private final CurrentAccountRepository currentAccountRepository;

  @Autowired
  public AccountService(
      SavingsAccountRepository savingsAccountRepository,
      CurrentAccountRepository currentAccountRepository) {
    this.savingsAccountRepository = savingsAccountRepository;
    this.currentAccountRepository = currentAccountRepository;
  }

  public AccountDTO createAccount(@NonNull AccountDTO accountDTO) {
    // Can be replaced with factory pattern if this is a composite service
    // containing other services.
    switch (accountDTO.getAccountType()) {
      case "CURRENT":
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setCurrentAccountYear(LocalDate.now().getYear());
        currentAccount.setAccountBalance(accountDTO.getInitialCredit());
        currentAccount.setCustomerId(accountDTO.getCustomerId());
        currentAccount.setAccountNumber(accountNumberGenerator());

        currentAccountRepository.save(currentAccount);

        accountDTO.setAccountNumber(currentAccount.getAccountNumber());
        break;
      case "SAVING":
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountBalance(accountDTO.getInitialCredit());
        savingsAccount.setCustomerId(accountDTO.getCustomerId());
        savingsAccount.setAccountNumber(accountNumberGenerator());

        savingsAccountRepository.save(savingsAccount);

        accountDTO.setAccountNumber(savingsAccount.getAccountNumber());
        break;
      default:
        throw new NationalBankOfKrakozhiaException("Invalid Account Type");
    }
    return accountDTO;
  }

  public List<Account> findAccountsByCustomerId(@NonNull Long customerId){
    return null;
  }

  private Long accountNumberGenerator() {
    return Math.abs(
        Float.valueOf(
                new Random().nextFloat() * (MAX_ACCOUNT_NUMBER_VALUE - MIN_ACCOUNT_NUMBER_VALUE))
            .longValue());
  }
}
