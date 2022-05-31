package net.wsm.filters;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.Order;

import net.wsm.helper.UserManager;

@WebFilter(filterName = "logginFilter", urlPatterns = { "/issues", "/admin/*", "/issue/*", "/comment" })
@Order(1)
public class LogginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {

		System.out.print("filter 1 : loggin filter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		boolean signedIn = (boolean) session.getAttribute("isSignedIn");
		LocalDateTime TokenExp = (LocalDateTime) session.getAttribute("Exp");
			if (signedIn || TokenExp.isBefore(LocalDateTime.now())) {
				System.out.print("filter 1 : loggin filter");
				return;
			}
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
	}
}