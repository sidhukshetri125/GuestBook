package com.guestbook.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.guestbook.model.Feedback;



@SpringBootApplication
@AutoConfigureMockMvc
@WebMvcTest
@RunWith(MockitoJUnitRunner.class)
public class FeedbackTest {
	@InjectMocks
	 Feedback feedback;
	
	
	@Test
	public void testFeedBack_toString() {
		
		Feedback fb = new Feedback();
		byte[] feedbackImage = null;
		fb.setFeedbackImage(feedbackImage);
		fb.setFeedbackImageName("abc.jpg");
		fb.setFeedbackText("hello");
		Date feedbackTime = new Date();
		fb.setTimestamp(feedbackTime);
		fb.setFirstName("sidhu");
		fb.setId( 1);	 
		 
		feedback.toString();
	}
	
	

}
