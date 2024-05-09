package beauty.customer.service;

import beauty.customer.dto.CustomerEditDto;
import beauty.customer.dto.CustomerRegisterDto;
import beauty.customer.dto.CustomerResponseDto;

public interface CustomerService {
	
	CustomerResponseDto addCustomer(CustomerRegisterDto customerRegisterDto);
	
	CustomerResponseDto getCustomerByEmail(String email);
	
	CustomerResponseDto getCustomerById(Long id);
	
	CustomerResponseDto editCustomer(String customerEmail,CustomerEditDto customerEditDto);
	
	CustomerResponseDto removeCustomer(Long id);

}
