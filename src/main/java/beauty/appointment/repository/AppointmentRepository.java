package beauty.appointment.repository;

import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import beauty.models.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
	Stream<Appointment> findByCustomerId(Long customerId);
	Stream<Appointment> findBySaloonId(Long saloonId);

}
