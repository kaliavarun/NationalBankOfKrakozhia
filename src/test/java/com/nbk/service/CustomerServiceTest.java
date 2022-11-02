package com.nbk.service;

import com.nbk.dao.domain.customer.Customer;
import com.nbk.dao.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CustomerServiceTest {

    private static final Long CUSTOMER_ID = 100000L;
    private static final String CUSTOMER_NAME = "John";
    private static final String CUSTOMER_SURNAME = "Doe";
    private final CustomerRepository repo = mock(CustomerRepository.class);
    private final CustomerService service = new CustomerService(repo);

    @BeforeEach
    void before() {
        given(repo.save(any(Customer.class)))
                .willAnswer(
                        (args) -> {
                            var customer = args.getArgument(0, Customer.class);
                            customer.setId(CUSTOMER_ID);
                            return customer;
                        });

        given(repo.findById(any(Long.class)))
                .willAnswer(
                        (args) -> {
                            Long customerId = args.getArgument(0, Long.class);
                            var customer = new Customer();
                            customer.setName(CUSTOMER_NAME);
                            customer.setSurname(CUSTOMER_SURNAME);
                            customer.setId(customerId);
                            return Optional.of(customer);
                        });
    }

    @Test
    void test_createCustomer_OK() {

        var customer = new Customer();
        customer.setName(CUSTOMER_NAME);
        customer.setSurname(CUSTOMER_SURNAME);

        assertThat(service.createCustomer(customer))
                .hasFieldOrPropertyWithValue("id", CUSTOMER_ID);
    }

    @ParameterizedTest
    @ValueSource(longs = {100000L})
    void test_findById_OK(Long customerId) {

        assertThat(service.findById(customerId))
                .isPresent()
                .get()
                .hasNoNullFieldsOrPropertiesExcept("accounts")
                .hasFieldOrPropertyWithValue("name", CUSTOMER_NAME);
    }
}
