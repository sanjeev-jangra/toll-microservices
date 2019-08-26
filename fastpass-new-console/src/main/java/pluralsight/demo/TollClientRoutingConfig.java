package pluralsight.demo;

import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

public class TollClientRoutingConfig {

	@Autowired
	IClientConfig ribbonClientConfig;
	
	public IRule ribbonRule(IClientConfig config) {
		return new WeightedResponseTimeRule();
	}
}
