package net.wsm.config;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends AbstractSecurityWebApplicationInitializer{
    public 

    // @Autowired
    // private UserDetailsService userDetailsService;

    // @Bean
    // public AuthenticationProvider authProvider(){

    //     DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    //     provider.setUserDetailsService(userDetailsService);
    //     BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
    //     provider.setPasswordEncoder(bCryptPasswordEncoder);
    //     return provider;
    // }


    // @Override
    // protected void configure(HttpSecurity http) throws Exception{
    //     // http
    //     //     .csrf().disable()
    //     //     .authorizeRequests().antMatchers("/login").permitAll()
    //     //     .anyRequest().authenticated()
    //     //     .and()
    //     //     .formLogin()
    //     //     .loginPage("/login").permitAll()
    //     //     .and()
    //     //     .logout().invalidateHttpSession(true)
    //     //     .clearAuthentication(true)
    //     //     .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    //     //     .logoutSuccessUrl("/home").permitAll();
    //     http.authorizeRequests((Request) -> Request.anyRequest().authenticated());
    //     http.formLogin();
    //     http.httpBasic();
    // }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http
    //         .authorizeHttpRequests((authz) -> authz
    //             .anyRequest().authenticated()
    //         )
    //         .httpBasic(withDefaults());
    //     return http.build();
    // }

    // @Bean
    // public WebSecurityCustomizer webSecurityCustomizer() {
    //     return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
    // }

}
