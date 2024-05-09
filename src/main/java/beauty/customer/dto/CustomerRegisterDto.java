package beauty.customer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRegisterDto {
	private String email;  
	private String firstName;
	private String lastName;
	private String password;

}
