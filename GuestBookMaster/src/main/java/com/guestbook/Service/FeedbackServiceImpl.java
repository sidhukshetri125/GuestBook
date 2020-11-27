
package com.guestbook.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guestbook.Repository.FeedbackRepository;
import com.guestbook.model.Feedback;

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

	public List<Feedback> findFeebackByUserId(Long id) throws Exception {
		return feedbackRepository.findAllByUserId(id);
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

	public void save(Feedback feedback) {
		feedbackRepository.save(feedback);
	}

	@Override
	public Optional<Feedback> findFeedbackById(long id) throws Exception {
		return feedbackRepository.findById(id);
	}

	@Override
	public List<Feedback> findFeebackByUserId(long id) throws Exception {
		return feedbackRepository.findAllByUserId(id);

	}

}
