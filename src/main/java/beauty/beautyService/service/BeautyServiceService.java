package beauty.beautyService.service;

import java.util.List;

import beauty.beautyService.dto.BeautyServiceAddEditDto;
import beauty.beautyService.dto.BeautyServiceResponseDto;

public interface BeautyServiceService {
	
	BeautyServiceResponseDto addService(Long saloonId, BeautyServiceAddEditDto beautyServiceEditDto);

	BeautyServiceResponseDto getService(Long serviceId);

	BeautyServiceResponseDto editService(Long serviceId, BeautyServiceAddEditDto beautyServiceEditDto);
	
	BeautyServiceResponseDto removeService(Long serviceId);

	List<BeautyServiceResponseDto> getSaloonServices(Long saloonId);

	BeautyServiceResponseDto getSaloonService(Long saloonId, Long serviceId);

}
