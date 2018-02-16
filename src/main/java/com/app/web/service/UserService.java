package com.app.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.dao.UserDAO;
import com.app.web.model.User;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public boolean emailExist(String email)
	{
		return userDAO.emailExist(email);
	}

	public int registerUser(String email, String password, String verifyCode, Integer verified, String status)
	{
		User userAccount = new User();
		userAccount.setEmail(email);
		userAccount.setPassword(password);
		userAccount.setVerifyCode(verifyCode);
		userAccount.setVerified(verified);
		userAccount.setStatus(status);
		
		return userDAO.saveUpdateUserAccount(userAccount);
	}

	public boolean isUserVerified(String email)
	{
		return userDAO.verifyUser(email);
	}

	public User getUserDetails(String email, String password)
	{
		return userDAO.getUserDetails(email, password);
	}
	
	
}
