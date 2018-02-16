package com.app.session;

import javax.servlet.http.HttpServletRequest;

public class JSPIntializerWeb
{
    boolean isLoggedIn = false;
    UserSession session = null;
    String sessionId;
    
    public JSPIntializerWeb(HttpServletRequest req)
    {
        session = (UserSession) req.getSession().getAttribute("USER_SESSION");
        if(session != null)
        {
            isLoggedIn = true;
            sessionId = session.getSessionId();
        }
    }
    
    public boolean isLoggedIn()
    {
        return isLoggedIn;
    }
    public void setLoggedIn(boolean isLoggedIn)
    {
        this.isLoggedIn = isLoggedIn;
    }
    public UserSession getSession()
    {
        return session;
    }
    public void setSession(UserSession session)
    {
        this.session = session;
    }
    public String getSessionId()
    {
        return sessionId;
    }
    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }
    
}
