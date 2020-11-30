package com.guestbook.securityconfig;

import static org.mockito.Mockito.mock;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

 

@RunWith(MockitoJUnitRunner.class)
public class TestSuccessAuthontication {
	
	
	@InjectMocks
	SuccessAuthontication successAuthontication;
	
	
	@Before
	public void onSetUp() {

	}
	
	@Test
	public void SuccessAthonitication() throws Exception {
		
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);
		
		Collection authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));
		Mockito.when(authentication.getAuthorities()).thenReturn(authorities);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		successAuthontication.onAuthenticationSuccess(request, response, authentication);		
	}
	
 
}
	
