package ozi.app.customer.services;

import ozi.app.customer.data.dtos.CustomerRequestDto;
import ozi.app.customer.data.dtos.CustomerResponseDto;
import ozi.app.customer.data.exceptions.CustomerException;
import ozi.app.customer.data.models.Customer;

import java.util.List;

public interface CustomerServices {
    CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) throws CustomerException;
    CustomerResponseDto findCustomerById(String id);
    List<Customer> findAllCustomers();
}
