package pluralsight.demo.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class ProxyFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		boolean isMobile = false;
		RequestContext ctx= RequestContext.getCurrentContext();
		String param = ctx.getRequest().getParameter("source");
		if(param !=null && param.equals("mobile")) {
			isMobile = true;
		}
		return isMobile;
	}

	@Override
	public Object run() throws ZuulException {
		System.out.println("Zuul toll proxy filter");
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	public ProxyFilter proxyFilter() {
		return new ProxyFilter();
	}
}
