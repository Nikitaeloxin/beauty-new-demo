package beauty.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity {
	
	@Column(name = "name")
    private String name;
	
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<Customer> customers;
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<Saloon> saloons;
	
	 @Override
	    public String toString() {
	        return "Role{" +
	                "id: " + super.getId() + ", " +
	                "name: " + name + "}";
	    }
}
