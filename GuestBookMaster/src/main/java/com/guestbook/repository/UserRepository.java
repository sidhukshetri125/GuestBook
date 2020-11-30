package com.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.guestbook.model.User;

/**
 * <h1>UserRepository interface </h1>
 * This repository extends the JpaRepository
 * 
 * @author Sidhu Kshetri
 * 
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
