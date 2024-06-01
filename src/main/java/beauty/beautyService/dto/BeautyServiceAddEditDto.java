package beauty.beautyService.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BeautyServiceAddEditDto {
	@NotBlank(message = "Service Name is mandatory field")
	@Size(min = 2, max = 100, message = "Min size is 2 symbols, Max size is 100 symbols")
	private String serviceName;

	@NotNull(message = "Price is mandatory field")
	@Min(value = 0, message = "Price cannot be negative")
	private Double price;

	@NotBlank(message = "Description is mandatory field")
	@Size( max = 100, message = "Max size is 100symbols")
	private String description;

}
