
package com.guestbook.Service;

import java.util.List;
import java.util.Optional;
import com.guestbook.model.Feedback;
 

public interface FeedbackService {	

	public List<Feedback> listAll();
	public void saveFeedback(Feedback feedback);	 
	public List<Feedback> findAllFeedbacks();
	public void deleteFeedbackById(int id) throws Exception;
	public Optional<Feedback> findFeedbackById(long id) throws Exception;
	public List<Feedback> findFeebackByUserId(long id) throws Exception;
	
}
