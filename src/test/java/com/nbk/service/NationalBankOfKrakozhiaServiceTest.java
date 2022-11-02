package com.nbk.service;

import com.nbk.dao.domain.account.Account;
import com.nbk.dao.domain.account.Transaction;
import com.nbk.dao.domain.customer.Customer;
import com.nbk.dao.repository.AccountRepository;
import com.nbk.dao.repository.CustomerRepository;
import com.nbk.dao.repository.TransactionRepository;
import com.nbk.dto.AccountDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class NationalBankOfKrakozhiaServiceTest {
    private static final Long ACCOUNT_ID = 100000L;
    private static final Long CUSTOMER_ID = 100000L;
    private static final Long TRANSACTION_ID = 100000L;
    private static final String CUSTOMER_NAME = "John";
    private static final String CUSTOMER_SURNAME = "Doe";
    private static final BigDecimal INITIAL_CREDIT = BigDecimal.valueOf(250000L);
    private final AccountRepository repoAccount = mock(AccountRepository.class);
    private final CustomerRepository repoCustomer = mock(CustomerRepository.class);
    private final TransactionRepository repoTransaction = mock(TransactionRepository.class);
    private final AccountService serviceAccount = new AccountService(repoAccount);
    private final CustomerService serviceCustomer = new CustomerService(repoCustomer);
    private final TransactionService serviceTransaction = new TransactionService(repoTransaction);
    private final NationalBankOfKrakozhiaService nationalBankOfKrakozhiaService =
            new NationalBankOfKrakozhiaService(serviceCustomer, serviceAccount, serviceTransaction);

    @BeforeEach
    void before() {

        given(repoCustomer.findById(any(Long.class)))
                .willAnswer(
                        (args) -> {
                            Long customerId = args.getArgument(0, Long.class);
                            var customer = new Customer();
                            customer.setName(CUSTOMER_NAME);
                            customer.setSurname(CUSTOMER_SURNAME);
                            customer.setId(customerId);
                            return Optional.of(customer);
                        });

        given(repoAccount.save(any(Account.class)))
                .willAnswer(
                        (args) -> {
                            Account account = args.getArgument(0, Account.class);
                            account.setAccountId(ACCOUNT_ID);
                            return account;
                        });

        given(repoTransaction.save(any(Transaction.class)))
                .willAnswer(
                        (args) -> {
                            var transaction = args.getArgument(0, Transaction.class);
                            transaction.setTransactionId(TRANSACTION_ID);
                            return transaction;
                        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"SAVINGS", "CURRENT"})
    void test_createAccount_OK(String accountType) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountType(accountType);
        accountDTO.setCustomerId(CUSTOMER_ID);
        accountDTO.setInitialCredit(INITIAL_CREDIT);

        assertThat(nationalBankOfKrakozhiaService.createAccount(accountDTO))
                .extracting(AccountDTO::getAccountNumber)
                .isNotNull();
    }
}
