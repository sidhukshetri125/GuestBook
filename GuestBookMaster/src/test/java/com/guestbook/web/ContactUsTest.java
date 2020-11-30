package com.guestbook.web;
 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 

import com.guestbook.repository.UserRepository;
import com.guestbook.service.UserServiceImpl;

@SpringBootApplication
@AutoConfigureMockMvc
@WebMvcTest
@RunWith(MockitoJUnitRunner.class)
public class ContactUsTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private ContactController contactController;

	@Mock
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepository userRepository; 
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Mock	
	private JavaMailSender mailSender;

	@Before
	public void onSetUp() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
	}
	
	
	@Test
	public void contactUsFailureTest() throws Exception {
		mockMvc.perform(get("/login/contactAdmin")).andExpect(status().isNotFound()).andReturn();
	}
	

}
