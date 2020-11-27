package com.guestbook.Service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.guestbook.Web.dto.UserRegistrationDto;
import com.guestbook.model.User;

public interface UserService extends UserDetailsService {

	public User save(UserRegistrationDto userRegistrationDto);

	public List<User> listAll();

	public User get(long id);

	public void delete(long id);

	public User findByEmail(String email);

}
