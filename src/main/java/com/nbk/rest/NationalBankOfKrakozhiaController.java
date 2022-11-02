package com.nbk.rest;

import com.nbk.dao.domain.account.Account;
import com.nbk.dao.domain.account.Transaction;
import com.nbk.dao.domain.customer.Customer;
import com.nbk.dto.AccountDTO;
import com.nbk.dto.TransactionDTO;
import com.nbk.service.NationalBankOfKrakozhiaService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class NationalBankOfKrakozhiaController {

    private final NationalBankOfKrakozhiaService service;

    @Autowired
    public NationalBankOfKrakozhiaController(NationalBankOfKrakozhiaService service) {
        this.service = service;
    }

    @PostMapping("/customer/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody @NonNull @Valid Customer customer) {
        return ResponseEntity.ok().body(service.createCustomer(customer));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@NonNull @PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.findCustomerById(id));
    }

    @GetMapping("/account/{number}")
    public ResponseEntity<Account> getAccount(@NonNull @PathVariable("number") Long number) {
        return ResponseEntity.ok().body(service.findAccountByAccountNumber(number));
    }

    @PostMapping("/account/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
        return ResponseEntity.ok().body(service.createAccount(accountDTO));
    }

    @PostMapping("/account/{accountNumber}/transaction/create")
    public ResponseEntity<Transaction> createTransaction(
            @RequestBody @Valid TransactionDTO transactionDTO) {
        return ResponseEntity.ok().body(service.createTransaction(transactionDTO));
    }
}
