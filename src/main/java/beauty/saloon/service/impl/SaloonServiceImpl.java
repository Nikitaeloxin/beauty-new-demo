package beauty.saloon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import beauty.customer.dto.CustomerResponseDto;
import beauty.models.Customer;
import beauty.models.Role;
import beauty.models.Saloon;
import beauty.models.Status;
import beauty.role.repository.RoleRepository;
import beauty.saloon.dto.SaloonEditDto;
import beauty.saloon.dto.SaloonRegisterDto;
import beauty.saloon.dto.SaloonResponseDto;
import beauty.saloon.repository.SaloonRepository;
import beauty.saloon.service.SaloonService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaloonServiceImpl implements SaloonService {
	
	private final SaloonRepository saloonRepository;
	private final RoleRepository roleRepository;
	private final ModelMapper modelMapper; 

	@Override
	public SaloonResponseDto addSaloon(SaloonRegisterDto saloonRegisterDto) {
		if (saloonRepository.existsByEmail(saloonRegisterDto.getEmail())) {
			throw new RuntimeException();
		}
		if (saloonRegisterDto.getEmail() == null || saloonRegisterDto.getAddress() == null
				|| saloonRegisterDto.getPassword() == null || saloonRegisterDto.getSaloonName() == null) {
			throw new RuntimeException();
		}
		Saloon saloon = modelMapper.map(saloonRegisterDto, Saloon.class);
		saloon.setStatus(Status.ACTIVE);
		Role saloonRole = roleRepository.findByName("ROLE_USER").orElseThrow(RuntimeException::new);
		List<Role> userRoles = new ArrayList<>();
        userRoles.add(saloonRole);
        saloon.setRoles(userRoles);
		saloon.setRate(0);
		saloonRepository.save(saloon);
		return modelMapper.map(saloon, SaloonResponseDto.class);
	}

	@Override
	public SaloonResponseDto getSaloonByEmail(String email) {
		Saloon saloon = saloonRepository.findByEmail(email).orElseThrow(RuntimeException::new);
		return modelMapper.map(saloon, SaloonResponseDto.class);
	}

	@Override
	public SaloonResponseDto getSaloonById(long id) {
		Saloon saloon = saloonRepository.findById(id).orElseThrow(RuntimeException::new);
		return modelMapper.map(saloon, SaloonResponseDto.class);
	}

	@Override
	public SaloonResponseDto editSaloon(String email, SaloonEditDto saloonEditDto) {
		Saloon saloon = saloonRepository.findByEmail(email).orElseThrow(RuntimeException::new);
		String saloonName = saloonEditDto.getSaloonName().trim();
		String address = saloonEditDto.getAddress().trim();
		if (saloonName == null || address == null) {
			throw new RuntimeException();
		}
		saloon.setSaloonName(saloonName);
		saloon.setAddress(address);		
		saloonRepository.save(saloon);
		return modelMapper.map(saloon, SaloonResponseDto.class);
	}

	@Override
	public SaloonResponseDto removeSaloon(long id) {
		Saloon saloon = saloonRepository.findById(id).orElseThrow(RuntimeException::new);
		saloonRepository.delete(saloon);
		return modelMapper.map(saloon, SaloonResponseDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<SaloonResponseDto> getSaloons() {
		return saloonRepository.findAllStream().map((Saloon s)->modelMapper.map(s, SaloonResponseDto.class)).toList();
	}

}