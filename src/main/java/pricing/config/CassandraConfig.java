package pricing.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

import pricing.repository.CustomerByServicePlanRepository;
import pricing.repository.CustomerRepository;
import pricing.service.CustomerService;
import pricing.service.CustomerServiceImpl;

public class CassandraConfig extends AbstractCassandraConfiguration {
	public static final String KEYSPACE = "pricing_keyspace";
	
	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}
	
	@Override
	protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
		CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE)
				.with(KeyspaceOption.DURABLE_WRITES, true);
		return Arrays.asList(specification);
	}
	
	@Override
	protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
		return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
	}
	
	@Override
	protected String getKeyspaceName() {
		return KEYSPACE;
	}
	
	@Override
	public String[] getEntityBasePackages() {
		return new String[] { "pricing" };
	}
	
	@Bean
	public CustomerService customerService(CustomerRepository customerRepository, 
											CustomerByServicePlanRepository customerByServicePlanRepository) {
		return new CustomerServiceImpl(customerRepository, customerByServicePlanRepository);
	}
	
}
