package beauty.saloon.controller;

import java.util.List;

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

import beauty.saloon.dto.SaloonEditDto;
import beauty.saloon.dto.SaloonRegisterDto;
import beauty.saloon.dto.SaloonResponseDto;
import beauty.saloon.service.SaloonService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/saloons")
@RestController
@RequiredArgsConstructor
public class SaloonController {
	private final SaloonService saloonService;

	@PostMapping("/register")
	ResponseEntity<SaloonResponseDto> register(@RequestBody SaloonRegisterDto saloonRegisterDto) {
		SaloonResponseDto response = saloonService.addSaloon(saloonRegisterDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	

	@GetMapping("/email/{email}")
	ResponseEntity<SaloonResponseDto> getSaloonByEmail(@PathVariable("email") String email) {
		SaloonResponseDto response = saloonService.getSaloonByEmail(email);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	

	@GetMapping("/id/{id}")
	ResponseEntity<SaloonResponseDto> getSaloonById(@PathVariable("id") Long id) {
		SaloonResponseDto response = saloonService.getSaloonById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	

	@PutMapping("/email/{email}")
	ResponseEntity<SaloonResponseDto> updateSaloon(@PathVariable("email") String email,
			@RequestBody SaloonEditDto saloonEditDto) {
		SaloonResponseDto response = saloonService.editSaloon(email, saloonEditDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	

	@DeleteMapping("/id/{id}")
	ResponseEntity<SaloonResponseDto> removeSaloon(@PathVariable("id") Long id) {
		SaloonResponseDto response = saloonService.removeSaloon(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	

	@GetMapping()
	ResponseEntity<List<SaloonResponseDto>> getSaloons() {
		return ResponseEntity.status(HttpStatus.OK).body(saloonService.getSaloons());
	}

}
