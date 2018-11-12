package pricing.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class CustomerByServicePlanKey implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String serviceplan;
	@PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private String country;
	@PrimaryKeyColumn(ordinal = 2, type = PrimaryKeyType.CLUSTERED)
	private UUID custId;
	@PrimaryKeyColumn(ordinal = 3, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
	private Date effectiveDate;

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getServiceplan() {
		return serviceplan;
	}

	public void setServiceplan(String serviceplan) {
		this.serviceplan = serviceplan;
	}

	public UUID getId() {
		return custId;
	}

	public void setId(UUID custId) {
		this.custId = custId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
