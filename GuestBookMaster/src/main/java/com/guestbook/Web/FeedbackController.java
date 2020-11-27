
package com.guestbook.Web;

import java.io.IOException;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.guestbook.Service.FeedbackService;
import com.guestbook.Service.UserService;
import com.guestbook.model.Feedback;

import com.guestbook.model.User;

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
		logger.info("Entered into UserController.addFeedback()");
		return "feedback-form";
	}

	@PostMapping("/submitFeedback")
	public String submitFeedback(@RequestParam("feedbackImage") MultipartFile feedbackImage,
			@RequestParam("feedbackText") String feedbackText, Model model, RedirectAttributes redirectAttributes) {

		logger.info("Entered into UserController.submitFeedback()");

		try {
			User user = userService.findByEmail(getUsersEmailDetails());
			String filname = StringUtils.cleanPath(feedbackImage.getOriginalFilename());
			Feedback feedback = new Feedback();
			feedback.setFeedbackimagename(filname);
			feedback.setUser(user);
			feedback.setFeedbacktext(feedbackText);
			feedback.setTimestamp(new Date());
			feedback.setFirstname(user.getFirstname());

			try {
				feedback.setFeedbackimage(feedbackImage.getBytes());
			} catch (IOException e) {

			}

			feedbackService.saveFeedback(feedback);

		} catch (Exception e) {

		}
		return "redirect:/viewFeedbackUser";

	}

	@GetMapping("/viewFeedbackUser")
	public String viewFeedbackUser(Model model) throws Throwable {
		logger.info("Entered into UserController.viewFeedbackUser()");
		 
		List<Feedback> feedbackList = null;

		try {

			User user = userService.findByEmail(getUsersEmailDetails());

			Long id = user.getId();

			feedbackList = feedbackService.findFeebackByUserId(id);

			logger.debug(" Feedback.viewFeedbackUser() is {}");
		} catch (Exception e) {
			logger.error("FeedbackController.viewFeedbackUser(){}{}", e.getMessage(), e);
		}
		model.addAttribute("feedbackList", feedbackList);
		model.addAttribute("loggedinUserName", getUsersEmailDetails());
		return "user-feedback";
	}

	
	@GetMapping("/adminView")
	public String viewFeedbackadmin(Model model) {
		List<Feedback> feedbackList = null;
		feedbackList = feedbackService.findAllFeedbacks();
		logger.debug("feedbackList{}", feedbackList);
		model.addAttribute("feedbackList", feedbackList);
		model.addAttribute("getLoggedUserName()", getUsersEmailDetails());
		return "admin-feedback";
	}

	private String getUsersEmailDetails() {
		String loggedUser = "";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			loggedUser = authentication.getName();
		}
		logger.info("Feedback Controller is {}", loggedUser);
		return loggedUser;
	}

}
