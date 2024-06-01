package beauty.saloon.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SaloonEditDto {
	@NotBlank(message = "Saloon name is mandatory field")
	@Size(min = 2, max = 50, message = "Size should be min 2symbols and max is 50")
	private String saloonName;

	@NotBlank(message = "Address is mandatory field")
	@Size(min = 2, max = 50, message = "Size should be min 2symbols and max is 50")
	private String address;

}
