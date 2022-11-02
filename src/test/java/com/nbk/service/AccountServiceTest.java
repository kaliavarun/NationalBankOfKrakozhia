package com.nbk.service;

import com.nbk.dao.domain.account.Account;
import com.nbk.dao.domain.account.AccountTypeEnum;
import com.nbk.dao.repository.AccountRepository;
import com.nbk.dto.AccountDTO;
import com.nbk.exception.NationalBankOfKrakozhiaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;

class AccountServiceTest {
    private static final Long ACCOUNT_ID = 100000L;
    private static final Long CUSTOMER_ID = 100000L;
    private final AccountRepository repo = mock(AccountRepository.class);
    private final AccountService service = new AccountService(repo);

    @BeforeEach
    void before() {
        given(repo.save(any(Account.class)))
                .willAnswer(
                        (args) -> {
                            Account account = args.getArgument(0, Account.class);
                            account.setAccountId(ACCOUNT_ID);
                            return account;
                        });

        given(repo.findByAccountNumber(any(Long.class)))
                .willAnswer(
                        (args) -> {
                            Long accountNumber = args.getArgument(0, Long.class);
                            Account account = new Account();
                            account.setAccountId(ACCOUNT_ID);
                            account.setAccountNumber(accountNumber);
                            account.setAccountType(AccountTypeEnum.SAVINGS);
                            account.setCustomerId(CUSTOMER_ID);

                            return account;
                        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"SAVINGS", "CURRENT"})
    void test_createAccount_OK(String accountType) {

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountType(accountType);
        accountDTO.setCustomerId(CUSTOMER_ID);

        assertThat(service.createAccount(accountDTO))
                .hasFieldOrPropertyWithValue("accountId", ACCOUNT_ID)
                .hasNoNullFieldsOrPropertiesExcept("accountBalance");
    }

    @ParameterizedTest
    @ValueSource(strings = {"XXXXXX"})
    void test_createAccount_NOK(String accountType) {

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountType(accountType);
        accountDTO.setCustomerId(CUSTOMER_ID);

        assertThatThrownBy(() -> service.createAccount(accountDTO))
                .isInstanceOf(NationalBankOfKrakozhiaException.class)
                .hasMessage("Invalid Account Type");
    }

    @ParameterizedTest
    @ValueSource(longs = {9999999999999999L})
    void test_findAccountsByAccountNumber_OK(Long accountNumber) {
        assertThat(service.findAccountsByAccountNumber(accountNumber))
                .hasFieldOrPropertyWithValue("accountId", ACCOUNT_ID);
    }
}
