package com.guestbook.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.guestbook.model.Feedback;
import com.guestbook.model.User;
import com.guestbook.repository.FeedbackRepository;
import com.guestbook.service.FeedbackService;
import com.guestbook.service.FeedbackServiceImpl;

@SpringBootApplication
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class UserFeedbackImplTest {

	@InjectMocks
	private FeedbackServiceImpl feedbackServiceImpl;

	@Mock
	private FeedbackRepository feedbackRepository;

	@Mock
	private FeedbackService feedbackService;

	@Test
	public void findFeedbackByIdTest() throws Exception {

		Feedback feedback = new Feedback();
		feedback.setId(5);
		Optional<Feedback> findid = Optional.of(feedback);
		Mockito.when(feedbackRepository.findById((long) Mockito.anyInt())).thenReturn(findid);

		Optional<Feedback> actulaValue = feedbackServiceImpl.findFeedbackById(1);

		verify(feedbackRepository, times(1)).findById((long) Mockito.anyInt());
		assertEquals(findid, actulaValue);
	}

	@Test
	public void deletefeedbackByidTest() throws Exception {

		doNothing().when(feedbackRepository).deleteById((long) Mockito.anyInt());
		feedbackServiceImpl.deleteFeedbackById(1);
		verify(feedbackRepository, times(1)).deleteById((long) 1);
	}

	@Test
	public void findAllFeedbacksTest() throws Exception {

		List<Feedback> allFeedback = null;
		Mockito.when(feedbackRepository.findAll()).thenReturn(allFeedback);

		List<Feedback> viewfeedback = feedbackServiceImpl.findAllFeedbacks();

		verify(feedbackRepository, times(1)).findAll();
		assertEquals(allFeedback, viewfeedback);
	}

	@Test
	public void savefeedbackTest() throws Exception {
		Feedback usersfeedback = new Feedback();
		Mockito.when(feedbackRepository.save(Mockito.any())).thenReturn(usersfeedback);
		feedbackServiceImpl.saveFeedback(usersfeedback);

		verify(feedbackRepository, times(1)).save(usersfeedback);
	}

	@Test
	public void saveFeedbackTest() throws Exception {
		Feedback usersfeedback = new Feedback();
		Mockito.when(feedbackRepository.save(Mockito.any())).thenReturn(usersfeedback);
		feedbackServiceImpl.saveFeedback(usersfeedback);

		verify(feedbackRepository, times(1)).save(usersfeedback);
	}

	@Test
	public void findallfeedbackTest() throws Exception {

		List<Feedback> eout = new ArrayList<>();
		Mockito.when(feedbackRepository.findAll()).thenReturn(eout);

		List<Feedback> aout = feedbackServiceImpl.findAllFeedbacks();

		verify(feedbackRepository, times(1)).findAll();
		assertEquals(eout, aout);
	}

	@Test
	public void findfeedbackByIdTest() throws Exception {

		Feedback feedback = new Feedback();
		feedback.setId(1);
		Optional<Feedback> optional = Optional.of(feedback);
		Mockito.when(feedbackRepository.findById(Mockito.anyLong())).thenReturn(optional);

		Optional<Feedback> aout = feedbackServiceImpl.findFeedbackById(1);

		verify(feedbackRepository, times(1)).findById(Mockito.anyLong());
		assertEquals(optional, aout);
	}

	@Test
	public void findfeedbackByuserIdTest2() throws Exception {

		Feedback feedback = new Feedback();
		feedback.setId(1);
		Optional<Feedback> optional = Optional.of(feedback);
		Mockito.when(feedbackRepository.findById(Mockito.anyLong())).thenReturn(optional);

		Optional<Feedback> aout = feedbackServiceImpl.findFeedbackById(1);

		verify(feedbackRepository, times(1)).findById(Mockito.anyLong());
		assertEquals(optional, aout);
	}

	@Test
	public void findfeedbackByuserIdTest() throws Exception {

		List<Feedback> eout = new ArrayList<>();
		Mockito.when(feedbackRepository.findAllByUserId(Mockito.anyLong())).thenReturn(eout);

		List<Feedback> aout = feedbackServiceImpl.findFeebackByUserId(2);

		verify(feedbackRepository, times(1)).findAllByUserId(Mockito.anyLong());
		assertEquals(eout, aout);
	}

	// listAll feedbacks

	@Test
	public void listAllfeedbacksTest() throws Exception {

		List<Feedback> eout = new ArrayList<>();
		Mockito.when(feedbackRepository.findAll()).thenReturn(eout);

		List<Feedback> aout = feedbackServiceImpl.listAll();

		verify(feedbackRepository, times(1)).findAll();
		assertEquals(eout, aout);
	}

}
