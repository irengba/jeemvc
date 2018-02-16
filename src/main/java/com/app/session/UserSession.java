package com.app.session;

import com.app.web.model.User;

public class UserSession
{
    private User user;
    private String sessionId;
    
    
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
    
	public void removeUser()
    {
		user = null;
    }
}
