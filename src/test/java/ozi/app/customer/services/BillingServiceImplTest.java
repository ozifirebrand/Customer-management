package ozi.app.customer.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ozi.app.customer.data.dtos.BillingDetailsRequestDto;
import ozi.app.customer.data.dtos.BillingDetailsResponseDto;
import ozi.app.customer.data.exceptions.CustomerException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Slf4j
class BillingServiceImplTest {

    @Autowired
    @Mock
    private BillingService billingService;

    private BillingDetailsRequestDto billingDetailsRequestDto;

    @BeforeEach
    void setUp() {
        billingDetailsRequestDto = new BillingDetailsRequestDto();
        billingDetailsRequestDto.setAccountNumber("1839048394");
        billingDetailsRequestDto.setTariff(1.24);
    }

    @AfterEach
    void tearDown() {
        billingService.deleteAll();
    }

    @Test
    void createBillingDetails() throws CustomerException {
        //given...
        //when
        BillingDetailsResponseDto responseDto = billingService.createBillingDetails(billingDetailsRequestDto);

        //assert
        assertThat(responseDto.getTariff()).isEqualTo(billingDetailsRequestDto.getTariff());
        assertThat(responseDto.getAccountNumber().length()).isEqualTo(13);
        log.info(responseDto.getAccountNumber());
        assertThat(responseDto.getId()).isNotNull();
        assertThat(responseDto).isNotNull();
    }

    @Test
    public void whenAccountNumberLengthIsLessThanThanTen_ThrowsException() throws CustomerException {
        //given ...
         billingDetailsRequestDto.setAccountNumber("19484595");
        //when
        //assert
        assertThatThrownBy(()->billingService.createBillingDetails(billingDetailsRequestDto))
                .isInstanceOf(CustomerException.class)
                .hasMessage("Wrong account number length.");

    }
    @Test
    public void whenAccountNumberLengthIsGreaterThanThanTen_ThrowsException() throws CustomerException {
        //given ...
         billingDetailsRequestDto.setAccountNumber("1948459784935");
        //when
        //assert
        assertThatThrownBy(()->billingService.createBillingDetails(billingDetailsRequestDto))
                .isInstanceOf(CustomerException.class)
                .hasMessage("Wrong account number length.");

    }
}