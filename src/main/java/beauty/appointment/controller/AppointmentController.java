package beauty.appointment.controller;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import beauty.appointment.dto.AddAppointmentDto;
import beauty.appointment.dto.AppointmentResponseDto;
import beauty.appointment.service.AppointmentService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/appointments")
@RestController
@RequiredArgsConstructor
public class AppointmentController {
	private final AppointmentService appointmentService;

	@PostMapping("/register")
	ResponseEntity<AppointmentResponseDto> addService(@RequestBody AddAppointmentDto addAppointmentDto) {
		AppointmentResponseDto response = appointmentService.addAppointment(addAppointmentDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/id/{id}")
	ResponseEntity<AppointmentResponseDto> getAppointment(@PathVariable("id") Long id) {
		AppointmentResponseDto response = new AppointmentResponseDto();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/id/{id}")
	ResponseEntity<AppointmentResponseDto> updateAppointment(@PathVariable("id") Long id,
			@RequestParam("time") LocalDateTime time) {
		AppointmentResponseDto response = appointmentService.editAppointment(id, time);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/id/{id}")
	ResponseEntity<AppointmentResponseDto> removeAppointment(@PathVariable("id") Long id) {
		AppointmentResponseDto response = appointmentService.removeAppointment(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/customer_id/{customerId}")
	ResponseEntity<List<AppointmentResponseDto>> getAllCustomerAppointments(
			@PathVariable("customerId") Long customerId) {
		return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAllCustomerAppointments(customerId));
	}

	@GetMapping("/saloon_id/{saloonId}")
	ResponseEntity<List<AppointmentResponseDto>> getAllSaloonAppointments(@PathVariable("saloonId") Long saloonId) {
		return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAllSaloonAppointments(saloonId));
	}

}
