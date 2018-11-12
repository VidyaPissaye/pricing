package pricing.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import pricing.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, UUID>{

}
