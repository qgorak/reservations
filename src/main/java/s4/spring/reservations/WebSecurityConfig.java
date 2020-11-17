package s4.spring.reservations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import s4.spring.reservations.services.UserDetailsServiceImpl;
 
@Configuration
@EnableWebMvc
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
    
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        CustomAuthenticationSuccessHandler handler = new CustomAuthenticationSuccessHandler();
        return handler;
    }
    
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
public AuthenticationFailureHandler authenticationFailureHandler() {
    return new CustomAuthenticationFailureHandler();
}
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        
       	//access rights 
        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**","/register", "assets/css/**", "/css/**").permitAll()
                .and()
                .formLogin()
                	.loginPage("/login")
                	.successHandler(successHandler())
                	.failureHandler(authenticationFailureHandler())
                .and()
                .logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessUrl("/")
                .and()
                .csrf().disable();
        
        httpSecurity.headers().frameOptions().disable();
        
        //redirect by role
        httpSecurity.authorizeRequests()
		.antMatchers("/lodgement").access("hasRole('ROLE_USER') or hasRole('ROLE_HOST') or hasRole('ROLE_ADMIN')")
		.antMatchers("/reservation").access("hasRole('ROLE_USER') or hasRole('ROLE_HOST') or hasRole('ROLE_ADMIN')")
		.antMatchers("/user/me").access("hasRole('ROLE_USER') or hasRole('ROLE_HOST') or hasRole('ROLE_ADMIN')")
		.antMatchers("/lodgement/").access("hasRole('ROLE_USER') or hasRole('ROLE_HOST') or hasRole('ROLE_ADMIN')")
		.antMatchers("/reservation/").access("hasRole('ROLE_USER') or hasRole('ROLE_HOST') or hasRole('ROLE_ADMIN')")
		.antMatchers("/user/me/").access("hasRole('ROLE_USER') or hasRole('ROLE_HOST') or hasRole('ROLE_ADMIN')")
		.and().formLogin().loginPage("/").and()
        .exceptionHandling()
        .accessDeniedPage("/");
        
  
    }
}