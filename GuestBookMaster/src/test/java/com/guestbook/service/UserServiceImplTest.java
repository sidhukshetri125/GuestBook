package com.guestbook.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

import com.guestbook.model.Feedback;
import com.guestbook.model.Role;
import com.guestbook.model.User;
import com.guestbook.repository.UserRepository;
import com.guestbook.service.UserServiceImpl;
import com.guestbook.dto.RegistrationDto;

@SpringBootApplication
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	
	
	
	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepository userRepository; 
	
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Test
	public void saveUserTest() throws Exception {
		
		RegistrationDto registrationDto =  new RegistrationDto();
		registrationDto.setEmail("sid@gmail.com");
		registrationDto.setFirstName("Sidhu");		 
		registrationDto.setFirstName("Kshetri");
		 
		
		User user = new User();
		user.setEmail("sid@gmail.com");
		user.setFirstName("Sidhu");
		user.setId(2);
		user.setLastName("Kshetri");
		user.setPassword("sidhu123");
		 
		 
		Collection<Role> roles = Arrays.asList(new Role("ROLE_USER"));
		user.setRoles(roles);	
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("sidhu123");
		
		User userexpected = userServiceImpl.save(registrationDto);
		assertEquals(userexpected.getEmail(), user.getEmail());
		 
	}
	
	
	@Test
	public void saveUserRoleTest() throws Exception {
		
		RegistrationDto registrationDto =  new RegistrationDto();
		registrationDto.setEmail("sid@gmail.com");		
		User user = new User();
		user.setEmail("sid@gmail.com");
			 
		Collection<Role> roles = Arrays.asList(new Role("ADMIN"));
		user.setRoles(roles);	
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("");
		
		User userexpected = userServiceImpl.save(registrationDto);
		assertEquals(userexpected.getEmail(), user.getEmail());
		assertEquals(userexpected.getRoles(), user.getRoles());
		
		 
	}
	
	@Test
	public void loadUserByUsernameTest() throws Exception {
		
		User user = new User();
		user.setEmail("sidhu@gmail.com");
		user.setFirstName("Mrunal");
		 user.setId(2);
		 user.setLastName("Kshetri");
		 
		user.setPassword("kshetri123");
		Collection<Role> roles = Arrays.asList(new Role("USER"));
		user.setRoles(roles);
		
		Mockito.when(userRepository.findByEmail("sidhu@gmail.com")).thenReturn(user);
		UserDetails userdetails  =userServiceImpl.loadUserByUsername("sidhu@gmail.com");
		assertEquals(userdetails.getUsername(), user.getEmail());
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void loadUserByUsernameFailureTest() throws Exception {
		
		Mockito.when(userRepository.findByEmail("kkr@gmail.com")).thenReturn(null);
		userServiceImpl.loadUserByUsername("kkr@gmail.com");
	}
 
	
	@Test
	public void deleteFeedbackByIdTest() throws Exception {

       doNothing().when(userRepository).deleteById((long) Mockito.anyInt());
       userServiceImpl.delete(1);
       
		verify(userRepository, times(1)).deleteById((long) 1);
	}
	
	@Test
	public void findByEmailTest() throws Exception {
		
		User user = new User();
		user.setEmail("sskshetri@gmail.com");
		user.setFirstName("rakesh");
		user.setId((int) 1);
		user.setLastName("sharma");
		user.setPassword("rakesh123");
		Collection<Role> roles = Arrays.asList(new Role("ADMIN"));
		user.setRoles(roles);
		Mockito.when(userRepository.findByEmail("sskshetri@gmail.com")).thenReturn(user);
		User userexpected =userServiceImpl.findByEmail("sskshetri@gmail.com");
		assertEquals(userexpected.getEmail(), user.getEmail());
		
	}

	

}
