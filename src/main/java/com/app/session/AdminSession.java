package com.app.session;

import com.app.admin.model.Admin;

public class AdminSession {
	private Admin admin;
	private String sessionId;
	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public void removeUser() {
		admin = null;
    }
}
