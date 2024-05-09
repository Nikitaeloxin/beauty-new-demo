package beauty.beautyService.dto;

import beauty.saloon.dto.SaloonResponseDto;
import lombok.Data;

@Data
public class BeautyServiceResponseDto {
	private Long id;
	private String serviceName;
	private Double price;
	private String description;
	private Integer rate;
	private SaloonResponseDto saloon;
}
