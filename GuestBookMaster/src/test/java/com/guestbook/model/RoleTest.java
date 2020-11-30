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

import com.guestbook.model.Role;

@SpringBootApplication
@AutoConfigureMockMvc
@WebMvcTest
@RunWith(MockitoJUnitRunner.class)
public class RoleTest {
	
	@InjectMocks
	 Role role;
	
	@Test
	public void RolTest() {
		
		Role role = new Role();
		role.setId(1);
		role.setName("Sidhu");
		 
		Collection<com.guestbook.model.Role> roles = Arrays.asList(new com.guestbook.model.Role("USER"));
		 
		role.toString();
		
	}
	

}
