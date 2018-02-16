package com.app.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User {
	public Integer userId;
	public String email;
	public String password;
	public String verifyCode;
	public Integer verified;
	public String status;
	public Date created;
	public Date lastSeen;
	
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "verify_code")
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	@Column(name = "verified")
	public Integer getVerified() {
		return verified;
	}
	public void setVerified(Integer verified) {
		this.verified = verified;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "created")
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@Column(name = "last_seen")
	public Date getLastSeen() {
		return lastSeen;
	}
	public void setLastSeen(Date lastSeen) {
		this.lastSeen = lastSeen;
	}
}
