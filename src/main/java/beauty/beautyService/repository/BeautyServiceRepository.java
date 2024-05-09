package beauty.beautyService.repository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import beauty.models.BeautyService;

public interface BeautyServiceRepository extends CrudRepository<BeautyService, Long> {
	
	Stream<BeautyService> findBySaloonId(Long saloonId);
	
	Optional<BeautyService> findByIdAndSaloonId(Long id,Long saloonId);

}
