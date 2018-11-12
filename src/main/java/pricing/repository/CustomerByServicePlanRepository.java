package pricing.repository;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pricing.domain.CustomerByServicePlan;
import pricing.domain.CustomerByServicePlanKey;

public interface CustomerByServicePlanRepository extends CrudRepository<CustomerByServicePlan, CustomerByServicePlanKey> {
	
	@Query("SELECT * FROM customerbyserviceplan WHERE serviceplan=?0 and country=?1")
	Iterable<CustomerByServicePlan> findByServicePlanForCountry (String serviceplan, String country);

	@Query("INSERT INTO customerbyserviceplan (serviceplan, country, custid, effectivedate, price) values (?0, ?1, ?2, ?3, ?4)")
	void updateServicePlanPrice(String serviceplan, String country, UUID custid, Date effectivedate, Double price);
	
	@Query("SELECT * FROM customerbyserviceplan WHERE serviceplan=?0 and country=?1 and effectivedate <= dateof(now()) and custid=?2 LIMIT 1")
	CustomerByServicePlan findCurrentServicePlanPriceForCustomer(String serviceplan, String country, UUID custid);
}
