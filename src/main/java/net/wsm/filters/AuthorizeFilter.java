package net.wsm.filters;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import net.wsm.model.User;

@Component
@Order(1)
public class AuthorizeFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                HttpServletResponse  myResponse = (HttpServletResponse) response;
                User u = (User)httpRequest.getSession().getAttribute("thisClient");
                System.out.println("Entered filter: " + u.getRole());
                if(u.getRole() <= 0){
                    throw new ServletException(request + " not HttpServletRequest");
                }
                chain.doFilter(request, response);
        
    }

}
