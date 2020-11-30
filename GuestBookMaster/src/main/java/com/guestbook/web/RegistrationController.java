package com.guestbook.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.guestbook.dto.RegistrationDto;
import com.guestbook.model.User;
import com.guestbook.service.UserService;

/**
 * <h1>User Registration Class</h1> The User Registration program implements an
 * application that simply register users and save into DB
 * 
 * @author Sidhu Kshetri
 * 
 */

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	private UserService userService;

	Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	public RegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public RegistrationDto registrationDto() {
		return new RegistrationDto();

	}

	@GetMapping
	public String showRegistrionForm() {
		logger.debug("Entering into registration form");
		return "registration-form";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") @Validated RegistrationDto registrationDto,
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
			logger.error("Exception in registerUserAccount.registerUserAccount() {}", e.getMessage(), e);
			return "redirect:/registration?error";
		}
		return "redirect:/login?success";
	}
}
