package ozi.app.customer.services;

import ozi.app.customer.data.dtos.BillingDetailsRequestDto;
import ozi.app.customer.data.dtos.BillingDetailsResponseDto;
import ozi.app.customer.data.exceptions.CustomerException;

public interface BillingService {
    BillingDetailsResponseDto createBillingDetails(BillingDetailsRequestDto billingDetailsRequestDto) throws CustomerException;

    boolean deleteAll();
}
