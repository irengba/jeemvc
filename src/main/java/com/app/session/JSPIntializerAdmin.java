package com.app.session;

import javax.servlet.http.HttpServletRequest;

public class JSPIntializerAdmin 
{
	boolean isLoggedIn = false;
	AdminSession session = null;
	String sessionId;
	
	public JSPIntializerAdmin(HttpServletRequest req)
    {
        session = (AdminSession) req.getSession().getAttribute("ADMIN_SESSION");
        if(session != null)
        {
            isLoggedIn = true;
            sessionId = session.getSessionId();
        }
    }
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public AdminSession getSession() {
		return session;
	}
	public void setSession(AdminSession session) {
		this.session = session;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
