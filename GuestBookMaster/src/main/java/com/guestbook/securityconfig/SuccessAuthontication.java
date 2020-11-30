package com.guestbook.securityconfig;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


/**
 * <h1>SuccessAuthontication Class</h1> The SuccessAuthontication program
 * for Admin and Users *  
 * 
 * @author Sidhu Kshetri
 * 
 */

@Configuration
public class SuccessAuthontication implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        
		// Two roles (ADMIN and USER)are defined in DB table name :roles_users	
		if (roles.contains("ADMIN") || roles.contains("USER")) {

			redirectStrategy.sendRedirect(request, response, "/welcomeguest");
		} else {
			redirectStrategy.sendRedirect(request, response, "/403");
		}
	}

}
