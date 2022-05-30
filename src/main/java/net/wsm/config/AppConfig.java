package net.wsm.config;
import java.util.Collections;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
/**
 * @author Ramesh Fadatare
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import net.wsm.filters.AuthorizeFilter;
import net.wsm.helper.UserManager;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "net.wsm", "net.wsm.filters"  })
public class AppConfig implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    @Scope(
      value = WebApplicationContext.SCOPE_SESSION, 
      proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserManager userManager() {
        UserManager userManager = new UserManager();
        return userManager;
    }

    // @Bean
    // public FilterRegistrationBean requestAuthorizeFilterBean() {
    //     AuthorizeFilter authorizeFilter=new AuthorizeFilter();
    //     final FilterRegistrationBean reg = new FilterRegistrationBean(authorizeFilter);
    //     reg.addUrlPatterns("/*");
    //     reg.setOrder(0); //defines filter execution order
    //     return reg;
    // }

    // @Autowired
    // public AppConfig(AuthorizeFilter authorizeFilter){
    //     this.authorizeFilter = authorizeFilter;
    // }

    // @Bean
    // public FilterRegistrationBean<AuthorizeFilter> AuthorizationFilterRegistration(){
    //     FilterRegistrationBean<AuthorizeFilter> filterRegistrationBean = new FilterRegistrationBean<>();
    //     filterRegistrationBean.setFilter(authorizeFilter);
    //     filterRegistrationBean.setUrlPatterns(Collections.singletonList("/account/*"));
    //     filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
    //     filterRegistrationBean.setOrder(1);
    //     return filterRegistrationBean;
    // }
}
