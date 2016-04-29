package com.web.app.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.web.app.dao.UserDAO;
import com.web.app.model.User;

public class UserDAOImpl implements UserDAO{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public boolean emailExist(String email)
	{
		Session session = sessionFactory.openSession();
		String hql = "from User where email=:email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		
		List<User> userEmail = query.list();
		
		session.close();
		
		if(userEmail.size() > 0)
			return true;
		else
			return false;
	}


	@Override
	public int saveUpdateUserAccount(User userAccount)
	{
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(userAccount);
		session.close();
		
		return userAccount.getUserId();
	}


	@Override
	public boolean verifyUser(String email)
	{
		Session session = sessionFactory.openSession();
		String hql = "from User where email=:email and verified=1";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		
		List<User> userLists = query.list();
		
		session.close();
		
		if(userLists.size() > 0)
			return true;
		else
			return false;
	}


	@Override
	public User getUserDetails(String email, String password)
	{
		Session session = sessionFactory.openSession();
		String hql = "from User where email=:email and password=:password";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		query.setParameter("password", password);
		
		List<User> userLists = query.list();
		
		session.close();
		
		if(userLists.size() > 0)
			return userLists.get(0);
		else
			return null;
	}
	
	
}