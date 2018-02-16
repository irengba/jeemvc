package com.app.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.admin.dao.AdminDAO;
import com.app.admin.model.Admin;

@Service
public class AdminService {

	@Autowired
	private AdminDAO adminDAO;

	public Admin getAdminInfo(String mobile, String password)
	{
		return adminDAO.getAdminInfo(mobile,password);
	}
	
}
