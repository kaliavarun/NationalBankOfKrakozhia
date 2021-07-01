package com.nbk.service;

import com.nbk.dao.domain.account.Account;
import com.nbk.dao.domain.account.Transaction;
import com.nbk.dao.domain.account.TransactionTypeEnum;
import com.nbk.dao.domain.customer.Customer;
import com.nbk.dto.AccountDTO;
import com.nbk.exception.NationalBankOfKrakozhiaException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class NationalBankOfKrakozhiaService {

  private final CustomerService customerService;
  private final AccountService accountService;
  private final TransactionService transactionService;

  @Autowired
  public NationalBankOfKrakozhiaService(
      CustomerService customerService,
      AccountService accountService,
      TransactionService transactionService) {
    this.customerService = customerService;
    this.accountService = accountService;
    this.transactionService = transactionService;
  }

  /**
   * @param accountDTO dto object to create account
   * @return accountDTO After account creation
   */
  @Transactional
  public AccountDTO createAccount(@NonNull AccountDTO accountDTO) {
    // 1. Check if valid customer
    this.customerService
        .findById(accountDTO.getCustomerId())
        .orElseThrow(() -> new NationalBankOfKrakozhiaException("No customer found!"));

    // 2. Create account.
    Account account = this.accountService.createAccount(accountDTO);

    // 3. Create transaction in new account if initial credit is not null
    if (Objects.nonNull(accountDTO.getInitialCredit())) {

      Transaction transaction = new Transaction();
      transaction.setTransactionAmount(accountDTO.getInitialCredit());
      transaction.setTransactionType(
          TransactionTypeEnum.CREDIT); // by default only credit is implemented
      transaction.setTransactionAccountId(account.getAccountId());

      this.transactionService.createTransaction(transaction);
    }
    //4. Set generated account number to DTO
    accountDTO.setAccountNumber(account.getAccountNumber());
    return accountDTO;
  }

  public Customer createCustomer(@NonNull Customer customer) {
    return customerService.createCustomer(customer);
  }

  public Customer findCustomerById(Long id) {
    return customerService.findById(id).orElse(null);
  }

  public Account findAccountByAccountNumber(Long number) {
    Account account = accountService.findAccountsByAccountNumber(number);
    return account;
  }
}
