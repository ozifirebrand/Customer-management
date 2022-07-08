package ozi.app.customer.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ozi.app.customer.data.dtos.CustomerRequestDto;
import ozi.app.customer.data.dtos.CustomerResponseDto;
import ozi.app.customer.data.exceptions.CustomerException;
import ozi.app.customer.data.models.BillingDetails;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Slf4j
class CustomerServicesImplTest {
    @Autowired
    private CustomerServices customerServices;

    private CustomerRequestDto customerRequestDto;
    private CustomerResponseDto customerResponseDto;


    @BeforeEach
    void setUp() throws CustomerException {
        BillingDetails billingDetails = new BillingDetails();
        billingDetails.setAccountNumber("1839048394");
        billingDetails.setTariff(1.24);

        customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setEmail("oziomaokoroafor@gmail.com");
        customerRequestDto.setFirstName("ozioma");
        customerRequestDto.setLastName("Okoroafor");
        customerRequestDto.setBillingDetails(billingDetails);

        customerResponseDto = customerServices.createCustomer(customerRequestDto);
    }

    @AfterEach
    void tearDown() {
        customerServices.deleteAllCustomers();
    }

    @Test
    void createCustomer() {
        //given...
        //when...
        //assert
        assertThat(customerResponseDto).isNotNull();
        assertThat(customerResponseDto.getEmail()).isEqualTo(customerRequestDto.getEmail());
        assertThat(customerResponseDto.getFirstName()).isEqualTo(customerRequestDto.getFirstName());
        assertThat(customerResponseDto.getLastName()).isEqualTo(customerRequestDto.getLastName());
        assertThat(customerResponseDto.getId()).isNotNull();
        assertThat(customerResponseDto.getBillingDetails().getId()).isNotNull();
        assertThat(customerResponseDto.getBillingDetails().getTariff()).isEqualTo(customerRequestDto.getBillingDetails().getTariff());


    }

    @Test
    public void findById() throws CustomerException {
        //given...
        //when
        CustomerResponseDto responseDto = customerServices.findCustomerById(customerResponseDto.getId());

        //assert
        assertThat(customerResponseDto.getId()).isEqualTo(customerResponseDto.getId());
        assertThat(customerResponseDto.getEmail()).isEqualTo(customerResponseDto.getEmail());
        assertThat(customerResponseDto.getFirstName()).isEqualTo(customerResponseDto.getFirstName());
        assertThat(customerResponseDto.getLastName()).isEqualTo(customerResponseDto.getLastName());
        assertThat(customerResponseDto.getBillingDetails().getTariff()).isEqualTo(responseDto.getBillingDetails().getTariff());
        assertThat(customerResponseDto.getBillingDetails().getAccountNumber()).isEqualTo(responseDto.getBillingDetails().getAccountNumber());

    }

    @Test
    void findAllCustomers() throws CustomerException {
        BillingDetails billingDetails2 = new BillingDetails();
        billingDetails2.setAccountNumber("1839068394");
        billingDetails2.setTariff(1.24);

        CustomerRequestDto customerRequestDto2 = new CustomerRequestDto();
        customerRequestDto2 = new CustomerRequestDto();
        customerRequestDto2.setEmail("oziomaokoroaf2or@gmail.com");
        customerRequestDto2.setFirstName("ozioma");
        customerRequestDto2.setLastName("Okoroafor");
        customerRequestDto2.setBillingDetails(billingDetails2);

        customerResponseDto = customerServices.createCustomer(customerRequestDto2);
        assertThat(customerServices.findAllCustomers().size()).isEqualTo(2);
    }
}