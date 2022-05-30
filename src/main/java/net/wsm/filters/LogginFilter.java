package net.wsm.filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;

import net.wsm.helper.UserManager;
import net.wsm.model.User;

@WebFilter(filterName = "authorizeFilter", urlPatterns = {"/issues", "/issue/*"})
@Order(1)
public class LogginFilter implements Filter {
  
  @Override
  public void init (FilterConfig filterConfig) throws ServletException {
  }
  
  @Override
  public void doFilter (ServletRequest request, ServletResponse response,
                        FilterChain chain)
            throws IOException, ServletException {
      HttpServletRequest req = (HttpServletRequest) request;
      UserManager u = (UserManager)req.getSession().getAttribute("thisClient");
    //   loginModel lg = new loginModel("Jeff@gmail.com", "password");
    //     System.out.println(lg.getPassword());
      if(!u.getIsSignedIn()){
          return;
      }
      chain.doFilter(request, response);
  }
  
  @Override
  public void destroy () {
  }
}