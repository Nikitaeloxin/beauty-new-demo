package beauty.customer.controller;

import beauty.customer.dto.CustomerEditDto;
import beauty.customer.dto.CustomerRegisterDto;
import beauty.customer.dto.CustomerResponseDto;
import beauty.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/customers")
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    ResponseEntity<CustomerResponseDto> register(@Valid @RequestBody CustomerRegisterDto customerRegisterDto)
            throws MethodArgumentNotValidException {
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
                                                       @Valid @RequestBody CustomerEditDto customerEditDto)
            throws MethodArgumentNotValidException {
        CustomerResponseDto response = customerService.editCustomer(email, customerEditDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/id/{id}")
    ResponseEntity<CustomerResponseDto> removeCustomer(@PathVariable("id") Long id) {
        CustomerResponseDto response = customerService.removeCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
