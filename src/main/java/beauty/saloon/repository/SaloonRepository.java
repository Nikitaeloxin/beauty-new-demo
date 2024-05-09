package beauty.saloon.repository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import beauty.models.Saloon;

public interface SaloonRepository extends CrudRepository<Saloon, Long> {

	boolean existsByEmail(String email);

	Optional<Saloon> findByEmail(String email);
	
	@Query("select s from Saloon s")
	Stream<Saloon> findAllStream();

}
