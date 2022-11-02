package com.nbk.service;

import com.nbk.dao.domain.account.Account;
import com.nbk.dao.domain.account.Transaction;
import com.nbk.dao.domain.account.TransactionTypeEnum;
import com.nbk.dao.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TransactionServiceTest {

    private static final Long TRANSACTION_ID = 100000L;
    private static final Long ACCOUNT_ID = 100000L;
    private final TransactionRepository repo = mock(TransactionRepository.class);
    private final TransactionService service = new TransactionService(repo);

    @BeforeEach
    void before() {
        given(repo.save(any(Transaction.class)))
                .willAnswer(
                        (args) -> {
                            var transaction = args.getArgument(0, Transaction.class);
                            transaction.setTransactionId(TRANSACTION_ID);
                            return transaction;
                        });
    }

    @Test
    void test_createTransaction_OK() {
        Account account = new Account();
        account.setAccountId(ACCOUNT_ID);

        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(BigDecimal.valueOf(200000L));
        transaction.setTransactionType(TransactionTypeEnum.CREDIT);
        transaction.setAccount(account);

        assertThat(service.createTransaction(transaction))
                .hasFieldOrPropertyWithValue("transactionId", TRANSACTION_ID);
    }
}
