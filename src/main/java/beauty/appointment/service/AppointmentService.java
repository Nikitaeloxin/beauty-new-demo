package beauty.appointment.service;

import java.time.LocalDateTime;
import java.util.List;

import beauty.appointment.dto.AddAppointmentDto;
import beauty.appointment.dto.AppointmentResponseDto;

public interface AppointmentService {

	AppointmentResponseDto addAppointment(AddAppointmentDto addAppointmentDto);

	AppointmentResponseDto getAppointment(Long appointmentId);

	AppointmentResponseDto editAppointment(Long appointmentId, LocalDateTime time);

	AppointmentResponseDto removeAppointment(Long appointmentId);

	List<AppointmentResponseDto> getAllCustomerAppointments(Long CustomerId);

	List<AppointmentResponseDto> getAllSaloonAppointments(Long saloonId);

}
