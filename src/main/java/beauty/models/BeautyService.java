package beauty.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@Table(name = "beauty_services")
@EqualsAndHashCode(callSuper = true)
public class BeautyService extends BaseEntity {
	
	@Column(name = "service_name")
	private String serviceName;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "service_description")
	private String description;
	
	@Column(name = "rate")
	private Integer rate;
	
	@ManyToOne
	@JoinColumn(name = "saloon_id")
	private Saloon saloon;
	
	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
	private List<Appointment> appointments;

}
