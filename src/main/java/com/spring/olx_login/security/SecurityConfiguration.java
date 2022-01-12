package com.spring.olx_login.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.olx_login.filter.JwtFilter;

//@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	 UserDetailsService userDetailsService ;
	@Autowired
	JwtFilter  jwtFilter;
	
	
	@Bean
	public JwtFilter authenticationJwtTokenFilter() {
		return new JwtFilter();
	}

	
	public void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(userDetailsService);
		
	}
	
	 @Override
	 public void configure(HttpSecurity http) throws Exception 
	{	http.csrf().disable().authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasAnyRole("USER", "ADMIN")
			.antMatchers("/user/authenticate", "/user", "/token/validate", "/getuser", "/logout").permitAll()
			.antMatchers("/all").permitAll();
			//.anyRequest().authenticated()
			//.and().exceptionHandling()
			//.and().sessionManagement()
			//.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
			//http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public AuthenticationManager getauthentication() throws Exception
	{
		return super.authenticationManager();
	}
	 
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
}
