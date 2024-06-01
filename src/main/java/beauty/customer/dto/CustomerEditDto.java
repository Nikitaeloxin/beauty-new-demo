package beauty.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerEditDto {
	@NotBlank(message = "First name is mandatory field")
	@Size(min = 2, max = 50, message = "Size should be min 2symbols and max is 50")
	private String firstName;

	@NotBlank(message = "Last name is mandatory field")
	@Size(min = 2, max = 50, message = "Size should be min 2symbols and max is 50")
	private String lastName;	
}
