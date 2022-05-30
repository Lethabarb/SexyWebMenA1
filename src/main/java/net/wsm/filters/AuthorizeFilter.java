package net.wsm.filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import net.wsm.model.User;
import net.wsm.model.loginModel;

@WebFilter(filterName = "authorizeFilter", urlPatterns = "/admin/*")
public class AuthorizeFilter implements Filter {
  
  @Override
  public void init (FilterConfig filterConfig) throws ServletException {
  }
  
  @Override
  public void doFilter (ServletRequest request, ServletResponse response,
                        FilterChain chain)
            throws IOException, ServletException {
      System.out.println("-- In MyFilter --");
      HttpServletRequest req = (HttpServletRequest) request;
      System.out.println("URI: " + req.getRequestURI());
      User u = (User)req.getSession().getAttribute("thisClient");
      loginModel lg = new loginModel("Jeff@gmail.com", "password");
      System.out.println(lg.getPassword());
      if(u.getRole() <= 0){
          return;
      }
      chain.doFilter(request, response);
  }
  
  @Override
  public void destroy () {
  }
}