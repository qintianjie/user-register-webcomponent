package com.colorcc.user.register.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.colorcc.user.register.form.LoginForm;

@Controller
public class LoginController {
	
	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute LoginForm loginForm) {
		return "login";
	}
	
	@RequestMapping(value = "/doLogin", method = RequestMethod.GET)
	public String doLogin(@ModelAttribute @Valid LoginForm loginForm, BindingResult result) {
		if (logger.isDebugEnabled()) {
			logger.debug("Do login ...");
		}
		return "redirect:users";
	}
}
