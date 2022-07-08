package ozi.app.customer.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ozi.app.customer.data.dtos.CustomerRequestDto;
import ozi.app.customer.data.dtos.CustomerResponseDto;
import ozi.app.customer.data.exceptions.CustomerException;
import ozi.app.customer.data.models.Customer;
import ozi.app.customer.services.CustomerServices;

@RestController
@RequestMapping("api/customer/")
public class CustomerControllers {
    @Autowired
    private CustomerServices customerServices;
    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        try {
            CustomerResponseDto customerResponseDto = customerServices.createCustomer(customerRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(customerResponseDto);
        }
        catch (CustomerException customerException){
            return ResponseEntity.badRequest().body(customerException.getMessage());
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable String id){
        try {
            CustomerResponseDto customerResponseDto = customerServices.findCustomerById(id);
            return ResponseEntity.status(HttpStatus.OK).body(customerResponseDto);
        }
        catch (CustomerException customerException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customerException.getMessage());
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<?> findAllCustomers(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(customerServices.findAllCustomers());
        }
        catch (CustomerException customerException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customerException.getMessage());
        }
    }

}
