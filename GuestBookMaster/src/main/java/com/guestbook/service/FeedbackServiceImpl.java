
package com.guestbook.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guestbook.service.FeedbackService;
import com.guestbook.model.Feedback;
import com.guestbook.model.User;
import com.guestbook.repository.FeedbackRepository;

/**
 * <h1> FeedbackServiceImpl Class</h1>  
 * 
 * @author Sidhu Kshetri
 * 
 */

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Override
	public void saveFeedback(Feedback feedback) {
		feedbackRepository.save(feedback);
	}

	public List<Feedback> listAll() {
		return feedbackRepository.findAll();
	}

	@Override
	public List<Feedback> findAllFeedbacks() {
		return feedbackRepository.findAll();

	}

	public Optional<Feedback> findFeedbackById(int id) throws Exception {
		return feedbackRepository.findById((long) id);
	}

	@Override
	public void deleteFeedbackById(int id) throws Exception {
		feedbackRepository.deleteById((long) id);
	}

	@Override
	public List<Feedback> findFeebackByUserId(long id) throws Exception {
		return feedbackRepository.findAllByUserId(id);

	}

	@Override
	public Optional<Feedback> findFeedbackById(long id) throws Exception {
		return feedbackRepository.findById((long) id);
	}

	public User findByEmail(String email) {
		return ((FeedbackServiceImpl) feedbackRepository).findByEmail(email);

	}

}
