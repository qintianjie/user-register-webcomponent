package com.colorcc.user.register.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.colorcc.user.bean.UserBean;
import com.colorcc.user.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

	private final static Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	UserService userService;

	/**
	 * Get user list.
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView users() {
		int startRow = 0;
		int fetchRows = 100;
		
		ModelAndView mav = new ModelAndView();
		List<UserBean> userList = userService.findUser(0, 100);
		if (logger.isDebugEnabled()) {
			logger.debug("Query the user list, from row " + startRow + " to " + (startRow + fetchRows));
		}
		if (userList != null) {
			mav.addObject("userList", userList);
		}

		mav.setViewName("user/list");
		return mav;
	}
}