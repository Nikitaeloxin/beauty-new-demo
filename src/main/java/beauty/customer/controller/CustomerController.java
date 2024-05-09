package beauty.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import beauty.customer.dto.CustomerEditDto;
import beauty.customer.dto.CustomerRegisterDto;
import beauty.customer.dto.CustomerResponseDto;
import beauty.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/customers")
@RestController
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	@PostMapping("/register")
	ResponseEntity<CustomerResponseDto> register(@RequestBody CustomerRegisterDto customerRegisterDto) {
		CustomerResponseDto response = customerService.addCustomer(customerRegisterDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/email/{email}")
	ResponseEntity<CustomerResponseDto> getCustomerByEmail(@PathVariable("email") String email) {
		CustomerResponseDto response = customerService.getCustomerByEmail(email);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/id/{id}")
	ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable("id") Long id) {
		CustomerResponseDto response = customerService.getCustomerById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/email/{email}")
	ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable("email") String email,
			@RequestBody CustomerEditDto customerEditDto) {
		CustomerResponseDto response = customerService.editCustomer(email, customerEditDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/id/{id}")
	ResponseEntity<CustomerResponseDto> removeCustomer(@PathVariable("id") Long id) {
		CustomerResponseDto response = customerService.removeCustomer(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
