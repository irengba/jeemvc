package com.web.app.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.model.ResponsePojo;
import com.web.app.model.User;
import com.web.app.service.UserService;
import com.web.app.session.UserSession;
import com.web.app.util.Utility;
import com.web.app.util.Validate;

import sun.misc.BASE64Encoder;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Validate validate;
	
	@Autowired
	private Utility utility;
	
	
	/* SINGUP GET
	 * */
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String RegisterGet(Model model, HttpServletRequest req, HttpServletResponse res)
	{
		UserSession session = (UserSession) req.getSession().getAttribute("USER_SESSION");
		
		if(session != null)
		{
			return "redirect:/";
		}
		else
		{
			return "register";
		}
	}
	
	
	/* SINGUP POST
	 * */
	@RequestMapping(value="/register", produces="application/json")
	@ResponseBody
	public ResponsePojo RegisterPOST(Model model, HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		String email = req.getParameter("email") == null ? "" : req.getParameter("email");
		String password = req.getParameter("password") == null ? "" : req.getParameter("password");
		Integer verified = 1;
		String status = "enable";
		
		ResponsePojo reponseStatus = new ResponsePojo();
		
		SecureRandom random = new SecureRandom();
    	final String verifyCode = new BigInteger(130, random).toString(32);
		
    	boolean isPasswordValid = validate.validatePassword(password);
    	boolean isEmailValid = validate.validateEmail(email);
    	boolean isEmailExist = userService.emailExist(email);
    	
    	
    	if(isEmailValid)
    	{
    		if(!isEmailExist)
    		{
    			if(!password.equals("") && isPasswordValid)
    			{
    				password = utility.encrypt(password, "MD5", "UTF-8");
    				int userId = userService.registerUser(email, password, verifyCode, verified, status);
    				
    				reponseStatus.setStatus(200);
    				reponseStatus.setMessage("Account created successfully.");
    			}
    			else
    			{
    				reponseStatus.setStatus(400);
    				reponseStatus.setMessage("Password is not valid.");
    			}
    		}
    		else
    		{
    			reponseStatus.setStatus(400);
				reponseStatus.setMessage("Email already exist.");
    			model.addAttribute("message", "");
    		}
    	}
    	else
    	{
    		reponseStatus.setStatus(400);
			reponseStatus.setMessage("Email is not valid.");
    	}
    	
		return reponseStatus;
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String LoginGET(Model model, HttpServletRequest req, HttpServletResponse res)
	{
		UserSession session = (UserSession) req.getSession().getAttribute("USER_SESSION");
		
		if(session != null)
		{
			model.addAttribute("message", "Login Successful");
			
			return "redirect:/";
		}
		else
		{
			model.addAttribute("message", "Please login");
			
			return "login";
		}
	}
	
	
	
	@RequestMapping(value = "/login", produces="application/json")
	@ResponseBody
	public ResponsePojo LoginPOST(Model model, HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		String email = req.getParameter("email") == null ? "" : req.getParameter("email");
		String password = req.getParameter("password") == null ? "" : req.getParameter("password");
		
		ResponsePojo reponseStatus = new ResponsePojo();
		
    	boolean isEmailValid = validate.validateEmail(email);
    	boolean isEmailExist = userService.emailExist(email);
    	boolean isVerified = userService.isUserVerified(email);
    	password = utility.encrypt(password, "MD5", "UTF-8");
    	
    	if(isEmailValid)
    	{
    		if(isEmailExist)
    		{
    			if(isVerified)
    			{
    				User userDetails = userService.getUserDetails(email, password);
    				if(userDetails != null)
    				{
    					UserSession session = new UserSession();
    					session.setUser(userDetails);
    					session.setSessionId(req.getSession().getId());
    					req.getSession(true).setAttribute("USER_SESSION", session);
    					
    					reponseStatus.setStatus(200);
        				reponseStatus.setMessage("You have successfully login.");
    				}
    				else
    				{
    					reponseStatus.setStatus(400);
        				reponseStatus.setMessage("User details not found.");
    				}
    			}
    			else
    			{
    				reponseStatus.setStatus(400);
    				reponseStatus.setMessage("User not verified.");
    			}
    		}
    		else
    		{
    			reponseStatus.setStatus(400);
				reponseStatus.setMessage("Email doesn't exist. Please Register.");
    		}
    	}
    	else
    	{
    		reponseStatus.setStatus(400);
			reponseStatus.setMessage("Email not valid.");
    	}
    	
    	return reponseStatus;
	}
	
	
	/* LOGOUT
	 * */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String Logout(Model model, HttpServletRequest req, HttpServletResponse res)
	{
		req.getSession().invalidate();
//		req.getSession().removeAttribute("USER_SESSION");
		
		return "redirect:/";
	}
	
	
}
