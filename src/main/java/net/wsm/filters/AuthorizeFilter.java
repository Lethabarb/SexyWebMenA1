package net.wsm.filters;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
		HttpSession session = req.getSession();
		int role = (Integer) session.getAttribute("role");
		System.out.println("filter 2 : auth filter enter");
		if (role <= 0) {
			System.out.println("filter 2 : role <= 0");
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}