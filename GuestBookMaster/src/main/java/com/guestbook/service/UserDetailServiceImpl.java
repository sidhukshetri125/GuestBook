package com.guestbook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.guestbook.service.MyUserDetails;
import com.guestbook.model.User;
import com.guestbook.repository.UserRepository;


/**
 * <h1>UserDetailServiceImpl Class</h1>  
 * 
 * @author Sidhu Kshetri
 * 
 */

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Entering into loadUserByUsername{} ");

		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		logger.debug("Entering into loadUserByUsername{} " + user);
		return new MyUserDetails(user);

	}

}
