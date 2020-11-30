package com.guestbook.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.guestbook.model.Role;
import com.guestbook.model.User;
import com.guestbook.repository.UserRepository;
import com.guestbook.service.UserDetailServiceImpl;
import com.guestbook.service.UserServiceImpl;

@SpringBootApplication
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {
	
	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private UserDetailServiceImpl userDetailServiceImpl;
	
	@Mock
	private UserRepository userRepository; 
	
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	
	@Test
	public void testLoadUserByUsername_Success() throws Exception {
		
		User user = new User();
		user.setEmail("sidhu@gmail.com");
		user.setFirstName("sidhu@gmail.com");
		 user.setId(2);
		 user.setLastName("Kshetri");
		 
		user.setPassword("kshetri123");
		Collection<Role> roles = Arrays.asList(new Role("USER"));
		user.setRoles(roles);
		
		Mockito.when(userRepository.findByEmail("sidhu@gmail.com")).thenReturn(user);
		UserDetails userdetails  =userDetailServiceImpl.loadUserByUsername("sidhu@gmail.com");
		assertEquals(userdetails.getUsername(), user.getEmail());
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void testLoadUserByUsername_Failure() throws Exception {
		
		Mockito.when(userRepository.findByEmail("kkr@gmail.com")).thenReturn(null);
		userDetailServiceImpl.loadUserByUsername("kkr@gmail.com");
	}
	
	

}
