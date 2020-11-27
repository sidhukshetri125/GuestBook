package com.guestbook.Web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guestbook.Service.UserService;
import com.guestbook.Web.dto.UserRegistrationDto;
import com.guestbook.model.User;

/**
 * <h1>User Registration Class</h1> The User Registration program implements an
 * application that simply register users and save into DB
 * 
 * @author Sidhu Kshetri
 * 
 */

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserService userService;

	 
	Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();

	}

	@GetMapping
	public String showRegistrionForm() {
		logger.debug("Entering into registration form");
		return "registration-form";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") @Validated UserRegistrationDto registrationDto,
			BindingResult bindingResult) {
		try {

			User existing = userService.findByEmail(registrationDto.getEmail());
			if (existing != null) {
				bindingResult.rejectValue("email", null, "Account already registered");			 
			}

			if (bindingResult.hasErrors()) {				
				return "registration-form";
			}
			userService.save(registrationDto);
		} catch (Exception e) {
			logger.error("Some exception occured  while registerUserAccount.registerUserAccount() {}", e.getMessage(),
					e);
			return "redirect:/registration?error";
		}
		return "redirect:/login?success";
	}

}
