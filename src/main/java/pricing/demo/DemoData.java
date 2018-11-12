package pricing.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import pricing.domain.Customer;
import pricing.domain.CustomerByServicePlan;
import pricing.repository.CustomerByServicePlanRepository;
import pricing.repository.CustomerRepository;

@Component
public class DemoData implements ApplicationRunner {
	@Autowired
	private CustomerRepository custRepo;
	@Autowired
	private CustomerByServicePlanRepository custByServicePlanRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		custRepo.save(new Customer(UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db3a"), "Pete", "Sampras", "US", "1S", 30.0));
		custRepo.save(new Customer(UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db3b"), "Roger", "Federer", "CAN", "1S", 35.0));
		custRepo.save(new Customer(UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db3c"), "Rafael", "Nadal", "CAN", "2S", 45.0));
		custRepo.save(new Customer(UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db39"), "Novak", "Djokovic", "US", "2S", 40.0));
		custRepo.save(new Customer(UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db3d"), "Andy", "Roddick", "US", "2S", 40.0));
		custRepo.save(new Customer(UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db3e"), "Serena", "Williams", "CAN", "3S", 55.0));
		custRepo.save(new Customer(UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db37"), "Andy", "Murray", "CAN", "3S", 55.0));
		custRepo.save(new Customer(UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db35"), "Sam", "Querry", "CAN", "3S", 55.0));
		custRepo.save(new Customer(UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db3f"), "Venus", "Williams", "US", "3S", 50.0));
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		// Date today = new Date();
		Date effectivedate = formatter.parse("2018-11-11");
		custByServicePlanRepo.save(new CustomerByServicePlan("1S", "US", UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db3a"), effectivedate, 30.0));
		custByServicePlanRepo.save(new CustomerByServicePlan("1S", "CAN", UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db3b"), effectivedate, 35.0));
		custByServicePlanRepo.save(new CustomerByServicePlan("2S", "CAN", UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db3c"), effectivedate, 45.0));
		custByServicePlanRepo.save(new CustomerByServicePlan("2S", "US", UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db39"), effectivedate, 40.0));
		custByServicePlanRepo.save(new CustomerByServicePlan("2S", "US", UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db3d"), effectivedate, 40.0));
		custByServicePlanRepo.save(new CustomerByServicePlan("3S", "CAN", UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db3e"), effectivedate, 55.0));
		custByServicePlanRepo.save(new CustomerByServicePlan("3S", "CAN", UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db37"), effectivedate, 55.0));
		custByServicePlanRepo.save(new CustomerByServicePlan("3S", "CAN", UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db35"), effectivedate, 55.0));
		custByServicePlanRepo.save(new CustomerByServicePlan("3S", "US", UUID.fromString("c37d661d-7e61-49ea-96a5-68c34e83db3f"), effectivedate, 50.0));
	
	}	
}
