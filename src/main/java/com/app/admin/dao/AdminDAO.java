package com.app.admin.dao;

import com.app.admin.model.Admin;

public interface AdminDAO {

	Admin getAdminInfo(String mobile, String password);

}
