package pricing.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import pricing.domain.Customer;
import pricing.domain.CustomerByServicePlan;

public interface CustomerService {
	Customer getCustomer(UUID id);
	List<Customer> getCustomers();
	Customer addCustomer(Customer cust);
	void updateCustomer(Customer cust);
	void deleteCustomer(UUID id);
	
	List<CustomerByServicePlan> getCustomersByServicePlanAll(String serviceplan, String country);
	void updatePricesOfCustomersByServicePlan(String serviceplan, String country, Map<String, Object> newplan);
}
