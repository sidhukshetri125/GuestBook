package com.guestbook.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.guestbook.model.Role;
import com.guestbook.model.User;
import com.guestbook.repository.UserRepository;
import com.guestbook.service.MyUserDetails;
import com.guestbook.service.UserDetailServiceImpl;
import com.guestbook.service.UserServiceImpl;


@SpringBootApplication
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class MyUserDetailsTest {
	
	@InjectMocks
	MyUserDetails myUserDetails;
	
	@Autowired
	private MockMvc mockMvc;

	@Mock
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepository userRepository; 
	
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	@Mock
	private UserDetailServiceImpl userDetailServiceImpl;
	
	public void onSetUp() {

	}	
	
	
	
	
	
	@Test
	public void testAuthority() throws Exception {
		User user = new User();
		
		user.setEmail("sidhu@gmail.com");
		user.setFirstName("sidhu@gmail.com");
		 user.setId(2);
		 user.setLastName("Kshetri");
		 
		user.setPassword("kshetri123");
		Collection<Role> roles = Arrays.asList(new Role("USER"));
		user.setRoles(roles);
		
		
		MyUserDetails myUserDetails = new MyUserDetails(user);
		myUserDetails.isAccountNonExpired();
		myUserDetails.isAccountNonLocked();
		myUserDetails.getPassword();
		myUserDetails.isCredentialsNonExpired();
		myUserDetails.isEnabled();
	
		
		Collection<? extends GrantedAuthority> userdetails =myUserDetails.getAuthorities();
		
		 assertThat(userdetails).hasSize(1);
		    assertThat(((ArrayList<GrantedAuthority>) userdetails).get(0).getAuthority()).isEqualTo("USER");
	}
	

}
