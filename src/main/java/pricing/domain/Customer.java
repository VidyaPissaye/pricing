package pricing.domain;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Table
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	private UUID custId;
	private String firstname;
	private String lastname;
	private String country;
	private String serviceplan;
	private Double price;
	
	public Customer() {
		
	}
	
	public Customer(UUID custId, String firstname, String lastname, String country, String serviceplan, Double price) {
		this.custId = custId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.country = country;
		this.serviceplan = serviceplan;
		this.price = price;
	}

	public String getFirstname() {
		return firstname;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastname) {
		this.lastname = lastname;
	}
	
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public UUID getCustId() {
		return custId;
	}

	public void setCustId(UUID custId) {
		this.custId = custId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getServiceplan() {
		return serviceplan;
	}

	public void setServiceplan(String serviceplan) {
		this.serviceplan = serviceplan;
	}


}
