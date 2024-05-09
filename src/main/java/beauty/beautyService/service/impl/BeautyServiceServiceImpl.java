package beauty.beautyService.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import beauty.beautyService.dto.BeautyServiceAddEditDto;
import beauty.beautyService.dto.BeautyServiceResponseDto;
import beauty.beautyService.repository.BeautyServiceRepository;
import beauty.beautyService.service.BeautyServiceService;
import beauty.exceptions.ServiceAlreadyExistException;
import beauty.models.BeautyService;
import beauty.models.Saloon;
import beauty.models.Status;
import beauty.saloon.repository.SaloonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeautyServiceServiceImpl implements BeautyServiceService {
	private final SaloonRepository saloonRepository;
	private final BeautyServiceRepository beautyServiceRepository;
	private final ModelMapper modelMapper;
	

	@Override
	public BeautyServiceResponseDto addService(Long saloonId, BeautyServiceAddEditDto beautyServiceEditDto) {
		
		if (beautyServiceEditDto.getServiceName() == ""
				|| beautyServiceEditDto.getDescription() == ""
				|| beautyServiceEditDto.getPrice() == -1) {
			throw new IllegalArgumentException();
		}
		
		Saloon saloon = saloonRepository.findById(saloonId).orElseThrow(EntityNotFoundException::new);
		
		if (beautyServiceRepository.findByServiceNameAndSaloonId(beautyServiceEditDto.getServiceName(),saloonId).isPresent()) {
			throw new ServiceAlreadyExistException(beautyServiceEditDto.getServiceName(),saloonId);
		}
		BeautyService service = modelMapper.map(beautyServiceEditDto, BeautyService.class);
		service.setSaloon(saloon);
		service.setRate(0);
		service.setStatus(Status.ACTIVE);
		beautyServiceRepository.save(service);
		return modelMapper.map(service, BeautyServiceResponseDto.class);
	}

	@Override
	public BeautyServiceResponseDto getService(Long serviceId) {
		BeautyService service = beautyServiceRepository.findById(serviceId).orElseThrow(EntityNotFoundException::new);
		return modelMapper.map(service, BeautyServiceResponseDto.class);
	}

	@Override
	public BeautyServiceResponseDto editService(Long serviceId, BeautyServiceAddEditDto beautyServiceEditDto) {
		BeautyService service = beautyServiceRepository.findById(serviceId).orElseThrow(EntityNotFoundException::new);
		if (beautyServiceEditDto.getServiceName() != "") {
			service.setServiceName(beautyServiceEditDto.getServiceName());
		}
		if (beautyServiceEditDto.getDescription() != "") {
			service.setDescription(beautyServiceEditDto.getDescription());
		}
		if (beautyServiceEditDto.getPrice() > -1) {
			service.setPrice(beautyServiceEditDto.getPrice());
		}
		beautyServiceRepository.save(service);
		return modelMapper.map(service, BeautyServiceResponseDto.class);
	}

	@Override
	public BeautyServiceResponseDto removeService(Long serviceId) {
		BeautyService service = beautyServiceRepository.findById(serviceId).orElseThrow(EntityNotFoundException::new);
		beautyServiceRepository.delete(service);
		return modelMapper.map(service, BeautyServiceResponseDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<BeautyServiceResponseDto> getSaloonServices(Long saloonId) {
		if (!saloonRepository.existsById(saloonId)) {
			throw new EntityNotFoundException();
		}
		return beautyServiceRepository.findBySaloonId(saloonId).map((BeautyService s)->modelMapper.map(s, BeautyServiceResponseDto.class)).toList();
	}

	@Override
	public BeautyServiceResponseDto getSaloonService(Long saloonId, Long serviceId) {
		BeautyService service = beautyServiceRepository.findByIdAndSaloonId(serviceId,saloonId).orElseThrow(EntityNotFoundException::new);
		return modelMapper.map(service, BeautyServiceResponseDto.class);
	}

}
