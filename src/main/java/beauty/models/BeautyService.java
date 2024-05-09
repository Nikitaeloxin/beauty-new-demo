package beauty.models;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "beauty_services")
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
