package beauty.appointment.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import beauty.appointment.dto.AddAppointmentDto;
import beauty.appointment.dto.AppointmentResponseDto;
import beauty.appointment.repository.AppointmentRepository;
import beauty.appointment.service.AppointmentService;
import beauty.beautyService.repository.BeautyServiceRepository;
import beauty.customer.repository.CustomerRepository;
import beauty.models.Appointment;
import beauty.models.BeautyService;
import beauty.models.Customer;
import beauty.models.Saloon;
import beauty.models.Status;
import beauty.saloon.repository.SaloonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
	private final AppointmentRepository appointmentRepository;
	private final SaloonRepository saloonRepository;
	private final CustomerRepository customerRepository;
	private final BeautyServiceRepository beautyServiceRepository;
	private final ModelMapper modelMapper;

	@Override
	public AppointmentResponseDto addAppointment(AddAppointmentDto addAppointmentDto) {
		if (addAppointmentDto.getTime() == null) {
			throw new IllegalArgumentException();
		}
		Saloon saloon = saloonRepository.findById(addAppointmentDto.getSaloonId()).orElseThrow(EntityNotFoundException::new);
		Customer customer = customerRepository.findById(addAppointmentDto.getCustomerId()).orElseThrow(EntityNotFoundException::new);
		BeautyService service = beautyServiceRepository.findByIdAndSaloonId(addAppointmentDto.getServiceId(),addAppointmentDto.getSaloonId())
				.orElseThrow(EntityNotFoundException::new);
		Appointment appointment = modelMapper.map(addAppointmentDto, Appointment.class);
		appointment.setSaloon(saloon);
		appointment.setCustomer(customer);
		appointment.setService(service);
		appointment.setStatus(Status.ACTIVE);
		appointmentRepository.save(appointment);
		return modelMapper.map(appointment, AppointmentResponseDto.class);
	}

	@Override
	public AppointmentResponseDto getAppointment(Long appointmentId) {
		Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(EntityNotFoundException::new);
		return modelMapper.map(appointment, AppointmentResponseDto.class);
	}

	@Override
	public AppointmentResponseDto editAppointment(Long appointmentId, LocalDateTime time) {
		Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(EntityNotFoundException::new);
		appointment.setTime(time);
		appointmentRepository.save(appointment);
		return modelMapper.map(appointment, AppointmentResponseDto.class);
	}

	@Override
	public AppointmentResponseDto removeAppointment(Long appointmentId) {
		Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(EntityNotFoundException::new);
		appointmentRepository.delete(appointment);
		return modelMapper.map(appointment, AppointmentResponseDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<AppointmentResponseDto> getAllCustomerAppointments(Long customerId) {
		
		return appointmentRepository.findByCustomerId(customerId)
				.map((Appointment a)->modelMapper.map(a, AppointmentResponseDto.class)).toList();
	}

	@Transactional(readOnly = true)
	@Override
	public List<AppointmentResponseDto> getAllSaloonAppointments(Long saloonId) {
		return appointmentRepository.findBySaloonId(saloonId)
				.map((Appointment a)->modelMapper.map(a, AppointmentResponseDto.class)).toList();
	}

}
