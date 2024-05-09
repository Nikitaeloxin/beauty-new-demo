package beauty.beautyService.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import beauty.beautyService.dto.BeautyServiceAddEditDto;
import beauty.beautyService.dto.BeautyServiceResponseDto;
import beauty.beautyService.repository.BeautyServiceRepository;
import beauty.beautyService.service.BeautyServiceService;
import beauty.models.BeautyService;
import beauty.models.Saloon;
import beauty.models.Status;
import beauty.saloon.repository.SaloonRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeautyServiceServiceImpl implements BeautyServiceService {
	private final SaloonRepository saloonRepository;
	private final BeautyServiceRepository beautyServiceRepository;
	private final ModelMapper modelMapper;
	

	@Override
	public BeautyServiceResponseDto addService(Long saloonId, BeautyServiceAddEditDto beautyServiceEditDto) {
		if (saloonId == null || beautyServiceEditDto.getServiceName() == null
				|| beautyServiceEditDto.getDescription() == null
				|| beautyServiceEditDto.getPrice() == null) {
			throw new IllegalArgumentException();
		}
		
		Saloon saloon = saloonRepository.findById(saloonId).orElseThrow(RuntimeException::new);
		BeautyService service = modelMapper.map(beautyServiceEditDto, BeautyService.class);
		service.setSaloon(saloon);
		service.setRate(0);
		service.setStatus(Status.ACTIVE);
		beautyServiceRepository.save(service);
		return modelMapper.map(service, BeautyServiceResponseDto.class);
	}

	@Override
	public BeautyServiceResponseDto getService(Long serviceId) {
		BeautyService service = beautyServiceRepository.findById(serviceId).orElseThrow(RuntimeException::new);
		return modelMapper.map(service, BeautyServiceResponseDto.class);
	}

	@Override
	public BeautyServiceResponseDto editService(Long serviceId, BeautyServiceAddEditDto beautyServiceEditDto) {
		BeautyService service = beautyServiceRepository.findById(serviceId).orElseThrow(RuntimeException::new);
		if (beautyServiceEditDto.getServiceName() != null) {
			service.setServiceName(beautyServiceEditDto.getServiceName());
		}
		if (beautyServiceEditDto.getDescription() != null) {
			service.setDescription(beautyServiceEditDto.getDescription());
		}
		if (beautyServiceEditDto.getPrice() != null) {
			service.setPrice(beautyServiceEditDto.getPrice());
		}
		beautyServiceRepository.save(service);
		return modelMapper.map(service, BeautyServiceResponseDto.class);
	}

	@Override
	public BeautyServiceResponseDto removeService(Long serviceId) {
		BeautyService service = beautyServiceRepository.findById(serviceId).orElseThrow(RuntimeException::new);
		beautyServiceRepository.delete(service);
		return modelMapper.map(service, BeautyServiceResponseDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<BeautyServiceResponseDto> getSaloonServices(Long saloonId) {
		return beautyServiceRepository.findBySaloonId(saloonId).map((BeautyService s)->modelMapper.map(s, BeautyServiceResponseDto.class)).toList();
	}

	@Override
	public BeautyServiceResponseDto getSaloonService(Long saloonId, Long serviceId) {
		BeautyService service = beautyServiceRepository.findByIdAndSaloonId(serviceId,saloonId).orElseThrow(RuntimeException::new);
		return modelMapper.map(service, BeautyServiceResponseDto.class);
	}

}
