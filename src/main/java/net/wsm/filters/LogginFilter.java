package net.wsm.filters;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;

import net.wsm.helper.UserManager;

@WebFilter(filterName = "authorizeFilter", urlPatterns = { "/issues", "/issue/*" })
@Order(1)
public class LogginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
			
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getSession().getAttribute("thisClient") != null){
			UserManager u = (UserManager) req.getSession().getAttribute("thisClient");
			if (!u.getIsSignedIn() && u.getUser().getTokenExp().isAfter(LocalDateTime.now())) {
				return;
			}
		}
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
	}
}