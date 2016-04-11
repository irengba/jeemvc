package com.web.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class Validate {

	public boolean validatePassword(String password)
	{
		Pattern passwordPattern = Pattern.compile("((?=.*\\d)(?=.*[a-z]).{6,20})");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        
        if(passwordMatcher.matches())
        	return true;
        else
        	return false;
	}

	public boolean validateEmail(String email)
	{
		Pattern emailPattern = Pattern
                .compile("[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?");
        Matcher emailMatcher = emailPattern.matcher(email);
        
        if(emailMatcher.matches())
        	return true;
        else
        	return false;
	}

}
