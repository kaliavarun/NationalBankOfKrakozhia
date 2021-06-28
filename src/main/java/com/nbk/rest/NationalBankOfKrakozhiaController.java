package com.nbk.rest;

import com.nbk.dao.domain.customer.Customer;
import com.nbk.dto.AccountDTO;
import com.nbk.service.NationalBankOfKrakozhiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
public class NationalBankOfKrakozhiaController {

  @Autowired private NationalBankOfKrakozhiaService service;

  @PostMapping("/customer/create")
  public ResponseEntity<Customer> createCustomer(@RequestBody @Valid Customer customer) {
    return ResponseEntity.ok().body(service.createCustomer(customer));
  }

  @GetMapping("/customer/{id}")
  public ResponseEntity<Customer> getCustomer(@Valid @PathVariable("id") Long id) {
    return ResponseEntity.ok().body(service.findCustomerById(id));
  }

  @PostMapping("/account/create")
  public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
    return ResponseEntity.ok().body(service.createAccount(accountDTO));
  }
}
