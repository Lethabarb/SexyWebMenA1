package net.wsm.filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;

import net.wsm.helper.UserManager;
import net.wsm.model.User;

@WebFilter(filterName = "authorizeFilter", urlPatterns = "/admin/*")
@Order(2)
public class AuthorizeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getSession().getAttribute("thisClient") != null) {
			User u = ((UserManager) req.getSession().getAttribute("thisClient")).getUser();
			if (u.getRole() <= 0) {
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}