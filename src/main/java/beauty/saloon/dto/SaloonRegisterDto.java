package beauty.saloon.dto;

import lombok.Data;

@Data
public class SaloonRegisterDto {
	private String email;
	private String saloonName;
	private String password;
	private String address;
}
