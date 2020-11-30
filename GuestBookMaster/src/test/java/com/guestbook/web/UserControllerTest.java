package com.guestbook.web;

 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
 
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 

 
import com.guestbook.model.User;
import com.guestbook.repository.UserRepository;
import com.guestbook.service.UserServiceImpl;
 

@SpringBootApplication
@AutoConfigureMockMvc
@WebMvcTest
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	 
	
	@InjectMocks
	private UserController userController;

	@Mock
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepository userRepository; 
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private MockMvc mockMvc;
	

	@Before
	public void onSetUp() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@Test
	public void testAdmin() throws Exception {
		
		User user= new User() ;
		user.setFirstName("nam@gmail.com");
		Mockito.when(userServiceImpl.findByEmail(Mockito.anyString())).thenReturn(user);
		this.mockMvc.perform(get("/indexadmin")).andExpect(status().isOk());
		}
	
	
	 
}
