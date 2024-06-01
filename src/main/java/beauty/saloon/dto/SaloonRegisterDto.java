package beauty.saloon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SaloonRegisterDto {
	@NotBlank(message = "Email is mandatory field")
	private String email;

	@NotBlank(message = "Saloon name is mandatory field")
	private String saloonName;

	@NotBlank(message = "Password is mandatory field")
	private String password;

	@NotBlank(message = "Address is mandatory field")
	private String address;
}
