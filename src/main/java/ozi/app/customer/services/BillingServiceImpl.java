package ozi.app.customer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ozi.app.customer.data.dtos.BillingDetailsRequestDto;
import ozi.app.customer.data.dtos.BillingDetailsResponseDto;
import ozi.app.customer.data.exceptions.CustomerException;
import ozi.app.customer.data.models.BillingDetails;
import ozi.app.customer.data.repositories.BillingDetailsRepository;

@Service
public class BillingServiceImpl implements BillingService{
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private BillingDetailsRepository billingDetailsRepository;
    @Override
    public BillingDetailsResponseDto createBillingDetails(BillingDetailsRequestDto billingDetailsRequestDto) throws CustomerException {
        if ( billingDetailsRequestDto.getAccountNumber().isEmpty() || billingDetailsRequestDto.getTariff().isInfinite() )
            throw new CustomerException(("Add valid details."));

        if ( billingDetailsRequestDto.getAccountNumber().length() != 10 )
            throw new CustomerException("Wrong account number length.");

        billingDetailsRequestDto.setAccountNumber(billingDetailsRequestDto.getAccountNumber() + ".01");
        BillingDetails billingDetails = new BillingDetails();
        mapper.map(billingDetailsRequestDto, billingDetails);
        BillingDetails savedBillingDetails = billingDetailsRepository.save(billingDetails);
        BillingDetailsResponseDto billingDetailsResponseDto = new BillingDetailsResponseDto();
        mapper.map(billingDetails, billingDetailsResponseDto);
        return billingDetailsResponseDto;
    }

    @Override
    public boolean deleteAll() {
        billingDetailsRepository.deleteAll();
        return billingDetailsRepository.findAll().isEmpty();
    }


}
