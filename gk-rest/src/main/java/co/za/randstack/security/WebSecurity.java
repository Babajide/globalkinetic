package co.za.randstack.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;

import co.za.randstack.config.CustomAuthenticationSuccessHandler;


/**
 * The core of spring security.
 *
 * See inline comments for clarity
 */
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    
    private UserDetailsService userDetailsService;
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler; //invoked after login
    private SessionRegistry sessionRegistry;
    @Autowired
    public WebSecurity(final UserDetailsService userDetailsServiceImpl,
                       final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler, final SessionRegistry sessionRegistry) {
        this.userDetailsService = userDetailsServiceImpl;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.sessionRegistry = sessionRegistry;
    }
    
    
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.cors().and().csrf().disable().authorizeRequests()
                //allow access to auth resources
                .antMatchers(HttpMethod.POST, "/api/user/add" ).permitAll() //allow access here so users can register.
                .antMatchers("/h2/**").permitAll() //allow access to in memory db, h2 see application.properties
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(customAuthenticationSuccessHandler)
                .usernameParameter("user") //form param for username
                .passwordParameter("pass") //form param for password
                .loginPage("/api/user/login") //login page, default is /login
                .permitAll().and()
                .logout()
                .and()
                .sessionManagement()
                .maximumSessions(1).sessionRegistry(sessionRegistry);
        
        
        //for h2
        http.headers().frameOptions().disable();
        
        
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    
    
}
