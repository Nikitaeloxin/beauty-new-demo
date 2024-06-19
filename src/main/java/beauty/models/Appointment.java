package beauty.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity {
	
	@Column(name = "time")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime time;

	String description;
	
	@ManyToOne
	@JoinColumn(name = "saloon_id")
	Saloon saloon;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "service_id")
	BeautyService service;
	

}
