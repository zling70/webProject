package com.zl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	private String id;
	private String username;
	private String password;
	private String email;
	private Date birthday;
	private List<Address> alist = new ArrayList<Address>();
	public List<Address> getAlist() {
		return alist;
	}
	public void setAlist(List<Address> alist) {
		this.alist = alist;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username +",birthday="+birthday+ ", password="
				+ password + ", email=" + email + ", alist=" + alist + "]";
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
