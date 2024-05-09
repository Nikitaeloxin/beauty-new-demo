package beauty.appointment.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import beauty.beautyService.dto.BeautyServiceResponseDto;
import beauty.customer.dto.CustomerResponseDto;
import beauty.saloon.dto.SaloonResponseDto;
import lombok.Data;

@Data
public class AppointmentResponseDto {
	Long id;
	SaloonResponseDto saloon;
	BeautyServiceResponseDto saloonService;
	CustomerResponseDto customer;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime time;

}
