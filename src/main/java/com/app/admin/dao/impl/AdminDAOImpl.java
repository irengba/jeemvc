package com.app.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.app.admin.dao.AdminDAO;
import com.app.admin.model.Admin;

public class AdminDAOImpl implements AdminDAO{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public Admin getAdminInfo(String mobile, String password)
	{
		Session session = sessionFactory.openSession();
		String hql = "from Admin where mobile=:mobile and password=:password";
		Query query = session.createQuery(hql);
		query.setParameter("mobile", mobile);
		query.setParameter("password", password);
		
		List<Admin> adminLists = query.list();
		
		session.close();
		
		if(adminLists.size() > 0)
			return adminLists.get(0);
		else
			return null;
	}
	
}
