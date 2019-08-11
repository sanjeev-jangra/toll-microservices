package pluralsight.demo;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthCheck implements HealthIndicator {

	int errorcode = 0;
	
	@Override
	public Health health() {
		System.out.println("Health check performed and current errorcode is : "+ errorcode);
		if(errorcode > 2 && errorcode < 5) {
			errorcode++;
			return Health.down().withDetail("Custom error code ", errorcode).build();
		}
		errorcode++;
		return Health.up().build();
	}

	
}
