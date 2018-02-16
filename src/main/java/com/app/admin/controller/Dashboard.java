package com.app.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.session.AdminSession;

@Controller
public class Dashboard {
	
	@RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
	public String DashboardMain(Model model, HttpServletRequest req, HttpServletResponse res)
	{
		AdminSession session = (AdminSession) req.getSession().getAttribute("ADMIN_SESSION");
		
		if(session != null)
		{
			return "dashboard";
		}
		else
		{
			return "redirect:/admin";
		}
	}
	

}
