package com.colorcc.user.register.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.colorcc.user.bean.UserBean;
import com.colorcc.user.register.form.UserForm;
import com.colorcc.user.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;

	/**
	 * Get user by id.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView user(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/user");
		UserBean user = userService.loadUser(id);
		if (user != null) {
			mav.addObject("user", user);
		}
		return mav;
	}

	/**
	 * Show the page to create a new user.
	 * 
	 * @param userForm
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createUser(@ModelAttribute UserForm userForm) {
		return "user/create";
	}

	/**
	 * Do create user action.
	 * 
	 * @param userForm
	 * @return
	 */
	@RequestMapping(value = "/doCreateUser", method = RequestMethod.POST)
	public String doCreateUser(@ModelAttribute @Valid UserForm userForm, BindingResult result) {
		if (userForm.getPassword().trim().length() > 20) {
			result.rejectValue("password", null, "Password length is more than 20 chars.");
		}

		if (result.hasErrors()) {
			if (logger.isErrorEnabled()) {
				logger.error("There are " + result.getErrorCount() + " errors during validating.");
			}
			return "user/create";
		}
		
		if (logger.isInfoEnabled()) {
			if (userForm != null) {
				logger.info("Create new user, information is : " + userForm.getEmail() + "\t" + userForm.getPassword() + "\t" + userForm.getStatus());
			}
		}

		if (userForm != null) {
			UserBean userBean = new UserBean();
			String email = userForm.getEmail();
			userBean.setEmail(email);
			userBean.setPasswd(userForm.getPassword());

			// Make sure "salt" length no more than 8, default is "abcd1234"
			if (email != null & email.trim().length() > 0) {
				userBean.setSalt(email.substring(0, 8));
			} else {
				userBean.setSalt("abcd1234");
			}

			userBean.setRegisterTime(dateToString(new Date()));
			userBean.setStatus((byte) userForm.getStatus());

			userService.createUser(userBean);
		}
		return "redirect:/users";
	}

	/**
	 * Show page to update a user which id is {id}
	 * 
	 * @param userForm
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String updateUser(@ModelAttribute UserForm userForm, @PathVariable("id") Integer id) {

		UserBean user = userService.loadUser(id);
		if (user != null) {
			userForm.setId(user.getId());
			userForm.setEmail(user.getEmail());
			userForm.setPassword(user.getPasswd());
			userForm.setStatus(user.getStatus());
		}
		return "user/update";
	}

	@RequestMapping(value = "/{id}/doUpdateUser", method = RequestMethod.PUT)
	public String doUpdateUser(@ModelAttribute @Valid UserForm userForm, BindingResult result) {

		if (userForm.getPassword().trim().length() > 6) {
			result.rejectValue("password", null, "Password length is more than 6 chars.");
		}

		if (result.hasErrors()) {
			if (logger.isErrorEnabled()) {
				logger.error("There are " + result.getErrorCount() + " errors during validating.");
			}
			return "user/update";
		}

		if (logger.isInfoEnabled()) {
			if (userForm != null) {
				logger.info("Update user who's id is " + userForm.getId() + ", new user information is : " + userForm.getEmail() + "\t" + userForm.getPassword() + "\t" + userForm.getStatus());
			}
		}

		if (userForm != null) {
			UserBean userBean = new UserBean();
			String email = userForm.getEmail();
			userBean.setEmail(email);
			userBean.setId(userForm.getId());
			userBean.setPasswd(userForm.getPassword());

			// Make sure "salt" length no more than 8, default is "abcd1234"
			if (email != null & email.trim().length() > 0) {
				userBean.setSalt(email.substring(0, 8));
			} else {
				userBean.setSalt("abcd1234");
			}

			userBean.setRegisterTime(dateToString(new Date()));
			userBean.setStatus((byte) userForm.getStatus());

			userService.update(userBean);
		}

		return "redirect:/users";
	}

	/**
	 * Delete user by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
	public String userDelete(@PathVariable("id") Integer id) {
		if (logger.isInfoEnabled()) {
			logger.info("Delete user who's id is " + id);
		}
		userService.deleteUser(id);
		return "redirect:/users";
	}

	/**
	 * Format the date.
	 * 
	 * @param date
	 * @return
	 */
	private String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

}