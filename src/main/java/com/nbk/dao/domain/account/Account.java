package com.nbk.dao.domain.account;

import lombok.Data;
import lombok.Singular;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_ACCOUNT")
@Data
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID", nullable = false, unique = true)
    private Long accountId;

    @Column(name = "ACCOUNT_TYPE", nullable = false)
    private AccountTypeEnum accountType;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "ACCOUNT_NUMBER", nullable = false, unique = true)
    private Long accountNumber;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "account",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @Singular
    private List<Transaction> transactions = new ArrayList<>();

    /**
     * It's not a good design to have account balance as a field in the account table from consistency
     * and auditing point. Therefore, for the same of simplicity it will be calculated on the fly.
     */
    @Transient
    private BigDecimal accountBalance;

    @PostLoad
    public void setAccountBalance() {
        accountBalance =
                this.transactions.stream()
                        .map(
                                (transaction ->
                                        TransactionTypeEnum.DEBIT.equals(transaction.getTransactionType())
                                                ? transaction.getTransactionAmount().negate()
                                                : transaction.getTransactionAmount()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
