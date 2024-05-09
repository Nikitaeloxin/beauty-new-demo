package beauty.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "saloons")
public class Saloon extends BaseEntity {
	
	@Column(unique = true)
	private String email;
	
	@Column(name = "saloon_name")
	private String saloonName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "rate")
	private Integer rate;
	
	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "saloon", cascade = CascadeType.ALL)
	private List<BeautyService> services;
	
	@OneToMany(mappedBy = "saloon", cascade = CascadeType.ALL)
	private List<Appointment> appointments;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "saloons_roles",
    		joinColumns = @JoinColumn(name = "saloon_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
	private List<Role> roles;

}
