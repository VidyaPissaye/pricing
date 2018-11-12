package pricing.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pricing.domain.Customer;
import pricing.domain.CustomerByServicePlan;
import pricing.domain.CustomerByServicePlanKey;
import pricing.repository.CustomerByServicePlanRepository;
import pricing.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private final CustomerRepository customerRepository;
	
	private final CustomerByServicePlanRepository customerByServicePlanRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository,
			CustomerByServicePlanRepository customerByServicePlanRepository) {
		this.customerRepository = customerRepository;
		this.customerByServicePlanRepository = customerByServicePlanRepository; 
	}
	
	@Override
	public Customer getCustomer(UUID id) {
		Customer cust = this.customerRepository.findById(id).get();
		CustomerByServicePlan plan = this.customerByServicePlanRepository.findCurrentServicePlanPriceForCustomer(cust.getServiceplan(), cust.getCountry(), id);
		if(plan != null && cust.getPrice() != plan.getPrice()) {
			cust.setPrice(plan.getPrice());
			this.customerRepository.save(cust);    
		}
		return cust;
	}
	
	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<>(); 
		this.customerRepository.findAll().forEach(customers::add);
		return customers;
	}
	
	@Override
	public Customer addCustomer(Customer plan) {
		if(plan.getCustId() == null) {
			plan.setCustId(UUID.randomUUID());
		}
		this.customerRepository.save(plan);
		this.customerByServicePlanRepository.updateServicePlanPrice(plan.getServiceplan(), plan.getCountry(), plan.getCustId(), new Date(), plan.getPrice());
		return plan;
	}
	
	@Override
	public void updateCustomer(Customer plan) {
		Customer cust = this.customerRepository.findById(plan.getCustId()).get();
		if(cust != null) {
			CustomerByServicePlan custByPlan = new CustomerByServicePlan(plan.getServiceplan(), plan.getCountry(), plan.getCustId(), new Date(), plan.getPrice());
			this.customerByServicePlanRepository.deleteById(custByPlan.getCustomerByServicePlanKey());
			this.customerRepository.save(plan);
			this.customerByServicePlanRepository.save(custByPlan);
		}		
	}
	
	@Override
	public void deleteCustomer(UUID id) {
		Customer cust = this.customerRepository.findById(id).get();
		if(cust != null) {
			this.customerRepository.deleteById(id);
			CustomerByServicePlan custByPlan = new CustomerByServicePlan(cust.getServiceplan(), cust.getCountry(), cust.getCustId(), new Date(), cust.getPrice());
			this.customerByServicePlanRepository.deleteById(custByPlan.getCustomerByServicePlanKey());
		}
	}
	
	@Override
	public List<CustomerByServicePlan> getCustomersByServicePlanAll(String serviceplanId, String country) {
		return (List<CustomerByServicePlan>) this.customerByServicePlanRepository.findByServicePlanForCountry(serviceplanId, country);
	}

	@Override
	public void updatePricesOfCustomersByServicePlan(String serviceplanId, String country,
			Map<String, Object> newplan) {
		if(newplan.containsKey("price")) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date effectiveDate = new Date();
			try {
				effectiveDate = newplan.containsKey("effectivedate") ? formatter.parse(newplan.get("effectivedate").toString()) : effectiveDate;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				new Exception(e);
				e.printStackTrace();
			}
			Double newPrice = (Double) newplan.get("price");
			List<CustomerByServicePlan> customers = new ArrayList<>();
			customerByServicePlanRepository.findByServicePlanForCountry(serviceplanId, country)
			.forEach(customers::add);
			
			Set<UUID> uniqueCustomers = new HashSet<>();
			for(int i = 0; i < customers.size(); i++) {
				CustomerByServicePlan cust = customers.get(i);
				CustomerByServicePlanKey key = cust.getCustomerByServicePlanKey();
				if(!uniqueCustomers.contains(key.getId())) {
					CustomerByServicePlan custByPlan = new CustomerByServicePlan(key.getServiceplan(), key.getCountry(), key.getId(), effectiveDate, newPrice);
					this.customerByServicePlanRepository.save(custByPlan);
					uniqueCustomers.add(key.getId());
				} 
			}
		}
		else 
			new Exception("Price not available");
	}

}
