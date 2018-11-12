package pricing.domain;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class CustomerByServicePlan {

	@PrimaryKey
	private CustomerByServicePlanKey customerByServicePlanKey;
	private Double price;
	
	public CustomerByServicePlan() {
		
	}

	public CustomerByServicePlan(String serviceplan, String country, UUID custId, Date effectiveDate, Double price) {
		CustomerByServicePlanKey customerByServicePlanKey = new CustomerByServicePlanKey();
		customerByServicePlanKey.setId(custId);
		customerByServicePlanKey.setServiceplan(serviceplan);
		customerByServicePlanKey.setCountry(country);
		customerByServicePlanKey.setEffectiveDate(effectiveDate);
		this.setCustomerByServicePlanKey(customerByServicePlanKey);
		this.setPrice(price);
	}
	
	
	public CustomerByServicePlanKey getCustomerByServicePlanKey() {
		return customerByServicePlanKey;
	}

	public void setCustomerByServicePlanKey(CustomerByServicePlanKey customerByServicePlanKey) {
		this.customerByServicePlanKey = customerByServicePlanKey;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
