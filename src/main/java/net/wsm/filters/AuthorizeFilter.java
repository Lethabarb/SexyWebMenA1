package net.wsm.filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "authorizeFilter", urlPatterns = "/*")
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
      chain.doFilter(request, response);
  }
  
  @Override
  public void destroy () {
  }
}