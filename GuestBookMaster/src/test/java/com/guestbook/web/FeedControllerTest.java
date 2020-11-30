package com.guestbook.web;
 
 

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.guestbook.model.User;
import com.guestbook.model.Feedback;
import com.guestbook.repository.UserRepository;
import com.guestbook.service.FeedbackServiceImpl;
import com.guestbook.service.UserServiceImpl;
import com.guestbook.web.FeedbackController;



@RunWith(MockitoJUnitRunner.class)
public class FeedControllerTest {

    @InjectMocks
	private FeedbackController feedbackController;

	@Mock
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private FeedbackServiceImpl feedbackServiceImpl;
	
	@Mock
	private UserRepository userRepository; 

	@Captor
	ArgumentCaptor<String> stringCaptor;

	@Captor
	ArgumentCaptor<Integer> intCaptor;

	private MockMvc mockMvc;

	@Before
	public void onSetUp() {

//		Authentication authentication = mock(Authentication.class);
//		SecurityContext securityContext = mock(SecurityContext.class);
//		when(securityContext.getAuthentication()).thenReturn(authentication);
//		SecurityContextHolder.setContext(securityContext);		
//		Mockito.when(authentication.getName()).thenReturn("sidkshetri@gmail.com");
//		MockitoAnnotations.initMocks(this);
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(feedbackController).build();
	}

 
	@Test
	public void welcomeGuestFailureTest() throws Exception {
		mockMvc.perform(get("/guest/contact")).andExpect(status().isNotFound()).andReturn();
	}



	@Test
	public void addFeedbackTest() throws Exception {
		mockMvc.perform(get("/addFeedback")).andExpect(status().isOk()).andExpect(view().name("feedback-form"));
	}

	
	

	@Test
	public void submitFeedbackFailTest() throws Exception {	
		MockMultipartFile file = new MockMultipartFile("file", "abc.jpg", "Text", "bar".getBytes());
		mockMvc.perform(multipart("/submitFeedback").file(file).param("id", "1").param("feedbacktext", "b1"));			 
	}

	
}

