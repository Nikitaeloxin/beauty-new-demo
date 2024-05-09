package beauty.customer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import beauty.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long > {
	
	boolean existsByEmail(String email);
	
	Optional<Customer> findByEmail(String email);

}
