package beauty.saloon.dto;

import java.util.Set;

import beauty.role.dto.RoleDto;
import lombok.Data;

@Data
public class SaloonResponseDto {	
	private Long id;
	private String email;
	private String saloonName;
	private String address;
	private Integer rate;
	Set<RoleDto> roles;

}
