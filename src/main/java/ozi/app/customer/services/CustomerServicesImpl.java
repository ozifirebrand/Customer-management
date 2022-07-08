package ozi.app.customer.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ozi.app.customer.data.dtos.BillingDetailsRequestDto;
import ozi.app.customer.data.dtos.BillingDetailsResponseDto;
import ozi.app.customer.data.dtos.CustomerRequestDto;
import ozi.app.customer.data.dtos.CustomerResponseDto;
import ozi.app.customer.data.exceptions.CustomerException;
import ozi.app.customer.data.models.BillingDetails;
import ozi.app.customer.data.models.Customer;
import ozi.app.customer.data.repositories.BillingDetailsRepository;
import ozi.app.customer.data.repositories.CustomerRepository;

import java.util.List;
@Service
public class CustomerServicesImpl implements CustomerServices {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BillingDetailsRepository billingRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) throws CustomerException {

        Customer customer = new Customer();
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        if ( customerRequestDto==null ) throw new CustomerException("Cannot send empty request!");
        BillingDetails billingDetails = customerRequestDto.getBillingDetails();
        BillingDetails savedBillingDetails = billingRepository.save(billingDetails);
        mapper.map(customerRequestDto, customer);
        customer.setBillingDetails(savedBillingDetails);
        Customer savedCustomer = customerRepository.save(customer);
        mapper.map(savedCustomer, customerResponseDto);
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto findCustomerById(String id) {
        return null;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return null;
    }
}
