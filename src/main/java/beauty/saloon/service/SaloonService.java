package beauty.saloon.service;

import java.util.List;

import beauty.saloon.dto.SaloonEditDto;
import beauty.saloon.dto.SaloonRegisterDto;
import beauty.saloon.dto.SaloonResponseDto;

public interface SaloonService {

	SaloonResponseDto addSaloon(SaloonRegisterDto saloonRegisterDto);

	SaloonResponseDto getSaloonByEmail(String email);
	
	SaloonResponseDto getSaloonById(long id);

	SaloonResponseDto editSaloon(String email, SaloonEditDto saloonEditDto);

	SaloonResponseDto removeSaloon(long id);

	List<SaloonResponseDto> getSaloons();

}
