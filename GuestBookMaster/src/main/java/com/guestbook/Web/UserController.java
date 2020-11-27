package com.guestbook.Web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.guestbook.Repository.UserRepository;
import com.guestbook.Service.UserService;
import com.guestbook.Web.dto.UserRegistrationDto;
import com.guestbook.model.User;

/**
 * <h1>User Controller Class</h1> The User Controller program implements an
 * application that users edit,delete and save into the DB
 * 
 * @author Sidhu Kshetri
 * 
 */

@Controller
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	/**
	 * This method is used to viewUsersList.This is the simplest form of a class
	 * method
	 * 
	 * @param Model model
	 * @return admindashboard page - Admin can view the all the user details
	 *         inforamtion
	 */
	@GetMapping("/indexadmin")
	public String viewUsersList(Model model) {
		List<User> listusers = userService.listAll();
		logger.debug("listusers" + listusers);
		model.addAttribute("listusers", listusers);
		return "admindashboard";
	}

	/**
	 * This method is used to showEditUsersPage.This is the simplest form of a class
	 * method Edit user details and save into DB
	 * 
	 * @param id
	 * @return mav object
	 */

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditUsersPage(@PathVariable(name = "id") long id) {
		ModelAndView mav = new ModelAndView("edituser");
		User user = userService.get(id);
		logger.debug("user::" + user);
		mav.addObject("user", user);
		return mav;
	}

	/**
	 * This method is used to deleteUserDetails.This is the simplest form of a class
	 * method Delete user details and save into DB
	 * 
	 * @param id
	 * @return redirs to original page
	 */
	@RequestMapping("/delete/{id}")
	public String deleteUserDetails(@PathVariable(name = "id") long id) {
		logger.debug("id::" + id);
		userService.delete(id);
		return "redirect:/";
	}

	/**
	 * This method is used to error403. Delete user details and save into DB
	 * 
	 */

	@GetMapping("/403")
	public String error403() {
		return "403";

	}

	/**
	 * This method is used to save user details. Save user details and store into DB
	 * 
	 * @param userRegistrationDto
	 * @return redirs to original page
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") UserRegistrationDto userRegistrationDto) {
		logger.debug("iuserRegistrationDtod::" + userRegistrationDto);
		userService.save(userRegistrationDto);
		return "redirect:/";
	}

}
