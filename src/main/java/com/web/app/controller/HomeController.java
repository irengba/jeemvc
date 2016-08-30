package com.web.app.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.app.session.UserSession;


@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest req, HttpServletResponse res)
	{
		UserSession session = (UserSession) req.getSession().getAttribute("USER_SESSION");
		
		if(session != null)
		{
			model.addAttribute("message", "Login Successful");
		}
		else
		{
			model.addAttribute("message", "Please login");
		}
		
		return "landing";
	}
	
}
