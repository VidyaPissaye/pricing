package pricing.web;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pricing.domain.Customer;
import pricing.domain.CustomerByServicePlan;
import pricing.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public List<Customer> getAllCustomers() {
		return customerService.getCustomers();
	}
	
	@GetMapping(path = "/{id}")
	public Customer getCustomer(@PathVariable UUID id) {
		return customerService.getCustomer(id);
	}
	
	@PostMapping
	public void addCustomer(@RequestBody Customer cust) {
		customerService.addCustomer(cust);
	}
	
	@PutMapping
	public void updateCustomer(@RequestBody Customer cust) {
		customerService.updateCustomer(cust);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteCustomer(@PathVariable UUID id) {
		customerService.deleteCustomer(id);
	}
	
	@GetMapping(path = "/byserviceplans/{serviceplan}/bycountry/{country}")
	public List<CustomerByServicePlan> getCustomersByServicePlan(@PathVariable String serviceplan, @PathVariable String country) {
		return customerService.getCustomersByServicePlanAll(serviceplan, country);
	}
	
	@PatchMapping(path = "/byserviceplans/{serviceplan}/bycountry/{country}/updateprice")
	public void updatePricesOfCustomersByServicePlan(@PathVariable String serviceplan, @PathVariable String country, @RequestBody Map<String, Object> updatedplan) {
		customerService.updatePricesOfCustomersByServicePlan(serviceplan, country, updatedplan);
	}
}
