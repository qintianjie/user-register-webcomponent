package com.colorcc.user.errors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorResponseController {
	
	@RequestMapping(value = "/Error404.html")
	public String error404() {
		return "error404";
	}
	
	@RequestMapping(value = "/Error500.html")
	public String error500() {
		return "error500";
	}

}
