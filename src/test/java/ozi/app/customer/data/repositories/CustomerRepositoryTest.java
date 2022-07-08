package ozi.app.customer.data.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ozi.app.customer.data.models.Customer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findCustomerByEmail() {
        //given
        Customer customer = new Customer();
        customer.setEmail("oziomaokoroafor@gmail.com");
        customer.setFirstName("Ozi");
        customer.setLastName("Oma");
        Customer savedCustomer = customerRepository.save(customer);
        //when
        Customer foundCustomer = customerRepository.findCustomerByEmail(customer.getEmail());
        //assert
        assertThat(foundCustomer.getEmail()).isEqualTo(savedCustomer.getEmail());
        assertThat(foundCustomer.getFirstName()).isEqualTo(savedCustomer.getFirstName());
    }
}