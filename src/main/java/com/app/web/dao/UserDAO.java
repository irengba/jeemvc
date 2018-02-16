package com.app.web.dao;

import com.app.web.model.User;

public interface UserDAO {

	boolean emailExist(String email);

	int saveUpdateUserAccount(User userAccount);

	boolean verifyUser(String email);

	User getUserDetails(String email, String password);

}
