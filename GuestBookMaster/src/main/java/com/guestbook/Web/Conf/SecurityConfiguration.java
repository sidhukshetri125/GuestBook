package com.guestbook.Web.Conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.guestbook.Service.UserDetailServiceImpl;
import com.guestbook.Service.UserService;
import com.guestbook.Web.UserRegistrationController;

/**
 * <h1>Security Configuration Class</h1> The SecurityConfiguration program
 * provides the HttpSecurity, pssword encoder and dao authontication
 * 
 * @author Sidhu Kshetri
 * 
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

	@Autowired
	UserService userService;

	@Autowired
	private CustomAuthenticationSuccessHandler successHandler;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/registration").permitAll().antMatchers("/").hasRole("ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/user/**").hasAnyRole("USER", "ADMIN").and()
				.formLogin().loginPage("/login").loginProcessingUrl("/doLogin").successHandler(successHandler)
				.permitAll().and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/403");

		http.headers().defaultsDisabled().cacheControl();

	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		logger.debug("authenticationProvider auth::" + auth);
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		logger.debug("configure auth::" + auth);
	}

}
