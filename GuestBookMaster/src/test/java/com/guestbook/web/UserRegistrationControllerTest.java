package com.guestbook.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import com.guestbook.dto.RegistrationDto;
import com.guestbook.model.User;
import com.guestbook.repository.UserRepository;
import com.guestbook.service.UserServiceImpl;
import com.guestbook.web.RegistrationController;

@SpringBootApplication
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationControllerTest {

	@InjectMocks
	private RegistrationController registrationControllerTest;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserServiceImpl userServiceImpl;

	@Mock
	BindingResult bindingResult;

	@Before
	public void onSetUp() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(registrationControllerTest).build();
	}

	@Test
	public void regsiterUserTest() throws Exception {
		User user = new User();
		user.setEmail("sid@gmail.com");
		user.setId(1);
		user.setFirstName("sidhu");
		user.setLastName("Kshetri");
		user.setPassword("sk123");
		RegistrationDto dto = new RegistrationDto();
		dto.setEmail("sid@gmail.com");
		when(userServiceImpl.findByEmail(Mockito.anyString())).thenReturn(user);
		mockMvc.perform(post("/registration").flashAttr("user", dto)).andExpect(status().isOk());
	}

	@Test
	public void regsiterUserTest2() throws Exception {
		User usr = new User();
		usr.setEmail("sid@gmail.com");
		usr.setId(1);
		usr.setFirstName("sid");
		usr.setLastName("Ksh");
		usr.setPassword("sidh123");

		when(userServiceImpl.save(Mockito.any(RegistrationDto.class))).thenReturn(usr);

		RegistrationDto dto = new RegistrationDto();
		dto.setEmail("abc@bt.com");
		mockMvc.perform(post("/registration").flashAttr("user", dto)).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/login?success")).andReturn();

	}

	@Test
	public void registrationTest() throws Exception {

		mockMvc.perform(get("/registration")).andExpect(status().isOk()).andExpect(view().name("registration-form"))
				.andReturn();

	}

}
