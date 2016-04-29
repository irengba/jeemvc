package com.web.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.app.session.UserSession;

@Controller
public class DashBoardController {
	
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String Dashboad(Model model, HttpServletRequest req, HttpServletResponse res)
	{
		UserSession session = (UserSession) req.getSession().getAttribute("USER_SESSION");
		
		if(session != null)
		{
			return "dashboard";
		}
		else
		{
			return "redirect:/";
		}
	}

}
