package net.wsm.config;
import java.util.Collections;

import javax.servlet.DispatcherType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
/**
 * @author Ramesh Fadatare
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import net.wsm.filters.AuthorizeFilter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "net.wsm"  })
public class AppConfig {

    private AuthorizeFilter authorizeFilter;

    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Autowired
    public AppConfig(AuthorizeFilter authorizeFilter){
        this.authorizeFilter = authorizeFilter;
    }

    @Bean
    public FilterRegistrationBean<AuthorizeFilter> AuthorizationFilterRegistration(){
        FilterRegistrationBean<AuthorizeFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(authorizeFilter);
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/login"));
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
