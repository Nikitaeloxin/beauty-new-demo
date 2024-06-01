package beauty.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import beauty.customer.dto.CustomerEditDto;
import beauty.customer.dto.CustomerRegisterDto;
import beauty.customer.dto.CustomerResponseDto;
import beauty.customer.repository.CustomerRepository;
import beauty.customer.service.CustomerService;
import beauty.exceptions.UserAlreadyExistException;
import beauty.models.Customer;
import beauty.models.Role;
import beauty.models.Status;
import beauty.role.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final RoleRepository roleRepository;
	private final ModelMapper modelMapper;

	@Override
	public CustomerResponseDto addCustomer(CustomerRegisterDto customerRegisterDto) {
		if (customerRepository.existsByEmail(customerRegisterDto.getEmail())) {
			throw new UserAlreadyExistException(customerRegisterDto.getEmail());
		}

		Customer customer = modelMapper.map(customerRegisterDto, Customer.class);
		customer.setStatus(Status.ACTIVE);

		Role customerRole = roleRepository.findByName("ROLE_USER").orElseThrow(EntityNotFoundException::new);
		List<Role> userRoles = new ArrayList<>();
		userRoles.add(customerRole);
		customer.setRoles(userRoles);

		customerRepository.save(customer);
		return modelMapper.map(customer, CustomerResponseDto.class);
	}

	@Override
	public CustomerResponseDto getCustomerByEmail(String email) {
		Customer customer = customerRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
		return modelMapper.map(customer, CustomerResponseDto.class);
	}

	@Override
	public CustomerResponseDto getCustomerById(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		return modelMapper.map(customer, CustomerResponseDto.class);
	}

	@Override
	public CustomerResponseDto editCustomer(String email, CustomerEditDto customerEditDto) {
		Customer customer = customerRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
		customer.setFirstName(customerEditDto.getFirstName());
		customer.setLastName(customerEditDto.getLastName());
		customerRepository.save(customer);
		return modelMapper.map(customer, CustomerResponseDto.class);

	}

	@Override
	public CustomerResponseDto removeCustomer(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		customerRepository.delete(customer);
		return modelMapper.map(customer, CustomerResponseDto.class);
	}

}
