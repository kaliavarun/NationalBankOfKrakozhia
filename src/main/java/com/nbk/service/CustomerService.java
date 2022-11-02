package com.nbk.service;

import com.nbk.dao.domain.customer.Customer;
import com.nbk.dao.repository.CustomerRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(@NonNull Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> findById(@NonNull Long customerId) {
        return customerRepository.findById(customerId);
    }
}
