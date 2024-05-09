package beauty.customer.dto;

import java.util.Set;

import beauty.role.dto.RoleDto;
import lombok.Data;

@Data
public class CustomerResponseDto {
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	Set<RoleDto> roles; 
}
