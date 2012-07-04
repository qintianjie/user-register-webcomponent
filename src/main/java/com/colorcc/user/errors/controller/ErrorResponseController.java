package com.colorcc.user.errors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorResponseController {
	
	@RequestMapping(value = "/Error404.html")
	public ModelAndView user() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error404");
		return mav;
	}

}
