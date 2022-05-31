package net.wsm.filters;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;

import net.wsm.helper.UserManager;
import net.wsm.model.User;

@WebFilter(filterName = "authorizeFilter", urlPatterns = "/admin/*")
@Order(2)
public class AuthorizeFilter implements Filter {

	@Resource(name = "userManager")
    private UserManager userManager;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		System.out.println("filter 2 : auth filter enter");
		if (userManager.getUser() != null) {
			User u = userManager.getUser();
			System.out.println("filter 2 : role = " + u.getRole());
			if (u.getRole() <= 0) {
				System.out.println("filter 2 : role <= 0");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}