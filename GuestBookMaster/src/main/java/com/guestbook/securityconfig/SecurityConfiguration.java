package com.guestbook.securityconfig;

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
import com.guestbook.service.UserDetailServiceImpl;
import com.guestbook.service.UserService;
import com.guestbook.web.RegistrationController;

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

	Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	UserService userService;

	@Autowired
	private SuccessAuthontication successAuthontication;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// HttpSecurity configuration
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		logger.info("Entering into SecurityConfiguration.configure(){} ");
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/registration").permitAll().antMatchers("/").hasRole("ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/user/**").hasAnyRole("USER", "ADMIN").and()
				.formLogin().loginPage("/login").loginProcessingUrl("/doLogin").successHandler(successAuthontication)
				.permitAll().and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/403");

		http.headers().defaultsDisabled().cacheControl();

	}

	// DAO layer Authentication
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		logger.info("Entering into SecurityConfiguration.authenticationProvider(){} ");
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		logger.debug("authenticationProvider auth{}" + auth);
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	// AuthenticationManagerBuilder
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		logger.debug("configure auth::" + auth);
	}

}
