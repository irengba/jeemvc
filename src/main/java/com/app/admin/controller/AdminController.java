package com.app.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.admin.model.Admin;
import com.app.admin.service.AdminService;
import com.app.session.AdminSession;
import com.app.util.Utility;


@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private Utility utility;
	
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String AdminHome(Model model, HttpServletRequest req, HttpServletResponse res)
	{
		AdminSession session = (AdminSession) req.getSession().getAttribute("ADMIN_SESSION");
		
		if(session != null)
		{
			return "redirect:/admin/dashboard";
		}
		else
		{
			return "admin";
		}
		
	}
	
	
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String AdminLogin(Model model, HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		String mobile = req.getParameter("mobile") == null ? "" : req.getParameter("mobile");
		String password = req.getParameter("password") == null ? "" : req.getParameter("password");
		password = utility.encrypt(password, "MD5", "UTF-8");
		
		if(!mobile.equals("") && !password.equals(""))
    	{
			Admin adminInfo = adminService.getAdminInfo(mobile,password);
			if(adminInfo != null)
			{
				AdminSession session = new AdminSession();
				session.setAdmin(adminInfo);
				session.setSessionId(req.getSession().getId());
				req.getSession(true).setAttribute("ADMIN_SESSION", session);
			}
			else
			{
				model.addAttribute("status", "failure");
	    		model.addAttribute("message", "Credentials not matching.");
			}
    	}
		else
		{
			model.addAttribute("status", "failure");
    		model.addAttribute("message", "All fields are required.");
		}
		
		return "redirect:/admin";
	}
	
	
	/* LOGOUT
	 * */
	@RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
	public String Logout(Model model, HttpServletRequest req, HttpServletResponse res)
	{
		req.getSession().removeAttribute("ADMIN_SESSION");
		return "redirect:/admin";
	}
	
}
