
package com.guestbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.guestbook.model.Feedback;

/**
 * <h1>FeedbackRepository interface</h1> This repository extends the
 * JpaRepository
 * 
 * @author Sidhu Kshetri
 * 
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

	public List<Feedback> findAllByUserId(Long email) throws Exception;
}
