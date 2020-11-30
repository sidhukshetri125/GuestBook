
package com.guestbook.web;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.guestbook.model.Feedback;
import com.guestbook.model.Role;
import com.guestbook.model.User;
import com.guestbook.service.FeedbackService;
import com.guestbook.service.UserService;

/**
 * <h1>User FeedbackController Class</h1> The User can provide the feedbacks
 * based on text and image *
 * 
 * @author Sidhu Kshetri
 * 
 */

@Controller
public class FeedbackController {

	Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private FeedbackService feedbackService;

	@GetMapping("/addFeedback")
	public String addFeedback() {
		return "feedback-form";
	}

	
	/**
	 * This method is used to submitFeedback.This is the simplest form of a class
	 * method .All the feedback submit here
	 * 
	 * @param feedbackImage,feedbackText,model,redirectAttributes
	 * @return viewFeedbackUser
	 */
	@PostMapping("/submitFeedback")
	public String submitFeedback(@RequestParam("feedbackImage") MultipartFile feedbackImage,
			@RequestParam("feedbackText") String feedbackText, Model model, RedirectAttributes redirectAttributes) {

		try {
			User user = userService.findByEmail(getUsersEmailDetails());
			logger.info("submitFeedback(){} " + user);
			String filname = StringUtils.cleanPath(feedbackImage.getOriginalFilename());
			Feedback feedback = new Feedback();
			feedback.setFeedbackImageName(filname);
			logger.info("filname{} " + filname);
			feedback.setUser(user);
			feedback.setFeedbackText(feedbackText);
			feedback.setTimestamp(new Date());
			feedback.setFirstName(user.getFirstName());
			try {

				feedback.setFeedbackImage(feedbackImage.getBytes());
			} catch (IOException e) {
				logger.info("Exception occured in submitFeedback(){} ");

			}

			feedbackService.saveFeedback(feedback);

		} catch (Exception e) {

		}
		return "redirect:/viewFeedbackUser";

	}

	@GetMapping("/viewFeedbackUser")
	public String viewFeedbackUser(Model model) throws Throwable {

		List<Feedback> feedbackList = null;
		boolean isAdmin = false;
		try {
			User user = userService.findByEmail(getUsersEmailDetails());
			logger.info(" User is {}", user);

			Collection<Role> rolelist = user.getRoles();

			for (Role role : rolelist) {
				if (role.getName().equals("ADMIN")) {
					isAdmin = true;
				}
			}
			logger.info(" isAdmin {}", isAdmin);
			if (isAdmin) {
				feedbackList = feedbackService.findAllFeedbacks();
			} else {
				Long id = user.getId();
				logger.info(" user id {}", id);
				feedbackList = feedbackService.findFeebackByUserId(id);
			}

			logger.debug(" Feedback.viewFeedbackUser() is {}");
		} catch (Exception e) {
			logger.error("FeedbackController.viewFeedbackUser(){}{}", e.getMessage(), e);
		}
		model.addAttribute("feedbackList", feedbackList);
		model.addAttribute("getUsersEmailDetails", getUsersEmailDetails());
		return "user-feedback";
	}

	private String getUsersEmailDetails() {
		String logUser = "";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			logUser = authentication.getName();
		}
		logger.info("Feedback Controller is {}", logUser);
		return logUser;
	}
}
