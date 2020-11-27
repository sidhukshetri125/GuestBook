package com.guestbook.Web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <h1>User MainController Class</h1> Login , welcome user and admin user page
 * 
 * 
 * @author Sidhu Kshetri
 * 
 */
@Controller
public class MainController {

	@GetMapping("/login")
	public String showMyLoginPage() {
		return "login-user";
	}

	@GetMapping("/welcomeguest")
	public String home() {
		return "welcome-guest";
	}

}
