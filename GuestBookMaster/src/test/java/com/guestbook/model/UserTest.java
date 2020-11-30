package com.guestbook.model;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;



@SpringBootApplication
@AutoConfigureMockMvc
@WebMvcTest
@RunWith(MockitoJUnitRunner.class)
public class UserTest {
	
	@InjectMocks
	 UserTest user;
	
	@Test
	public void UserTest() {
		
		com.guestbook.model.User user = new com.guestbook.model.User();
		user.setEmail("sidkshetri@gmail.com");
		user.setFirstName("sidhu");
		user.setId((int) 1);
		user.setLastName("kshetri");
		user.setPassword("sid123");
		Collection<com.guestbook.model.Role> roles = Arrays.asList(new com.guestbook.model.Role("USER"));
		user.setRoles(roles );
		user.toString();
		
	}
	
	
	

}

