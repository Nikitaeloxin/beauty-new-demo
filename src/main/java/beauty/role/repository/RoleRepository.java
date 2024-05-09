package beauty.role.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import beauty.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Optional<Role> findByName(String name);

}
