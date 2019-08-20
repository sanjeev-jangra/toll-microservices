package pluralsight.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
public class DashboardController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@HystrixCommand(fallbackMethod="GetTollRateFallback")
	@RequestMapping("/dashboard")
	public String GetTollRate(@RequestParam int stationId, Model m) {

		TollRate tr = restTemplate.getForObject("http://pluralsight-toll-service/tollrate/" + stationId, TollRate.class);
		System.out.println("stationId: " + stationId);
		m.addAttribute("rate", tr.getCurrentRate());
		return "dashboard";
	}
	
	public String GetTollRateFallback(@RequestParam int stationId, Model m) {
		System.out.println("Fallback called GetTollRateFallback. Set default value to 1");
		m.addAttribute("rate", 1);
		return "dashboard";
	}
}
