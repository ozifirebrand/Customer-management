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
import java.util.Optional;

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
        if ( customerRepository.findCustomerByEmail(customerRequestDto.getEmail()) != null ){
            throw new CustomerException("This user already exists!");
        }
        getBillingDetails(customerRequestDto, customer);
        Customer savedCustomer = customerRepository.save(customer);
        mapper.map(savedCustomer, customerResponseDto);
        return customerResponseDto;
    }

    private void getBillingDetails(CustomerRequestDto customerRequestDto, Customer customer) {
        BillingDetails billingDetails = customerRequestDto.getBillingDetails();
        BillingDetails savedBillingDetails = billingRepository.save(billingDetails);
        mapper.map(customerRequestDto, customer);
        customer.setBillingDetails(savedBillingDetails);
    }

    @Override
    public CustomerResponseDto findCustomerById(String id) throws CustomerException {
        CustomerResponseDto responseDto = new CustomerResponseDto();
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if ( optionalCustomer.isEmpty() ){
            throw new CustomerException("This customer does not exist!");
        }
        Customer customer = optionalCustomer.get();
        mapper.map(customer, responseDto);
        return responseDto;
    }

    @Override
    public boolean deleteAllCustomers(){
        customerRepository.deleteAll();
        billingRepository.deleteAll();
        return customerRepository.findAll().isEmpty();
    }

    @Override
    public List<Customer> findAllCustomers() throws CustomerException {
        List<Customer> customers = customerRepository.findAll();
        if ( customers.isEmpty() ){
            throw new CustomerException("There are no customers!");
        }
        return customers;
    }
}