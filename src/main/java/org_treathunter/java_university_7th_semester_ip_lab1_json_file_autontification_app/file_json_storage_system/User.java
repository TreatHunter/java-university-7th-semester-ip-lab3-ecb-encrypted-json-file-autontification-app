package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.file_json_storage_system;

import java.util.Objects;
import java.util.regex.Pattern;

public class User 
{
	public enum Role
	{
		admin,
		user
	}
	private String username;
	private String password;
	private Role role;
	private boolean isBanned;
	private boolean passwordRestictions;
	
	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public Role getRole() 
	{
		return role;
	}

	public void setRole(Role role) 
	{
		this.role = role;
	}

	public boolean isBanned() 
	{
		return isBanned;
	}

	public void setBanned(boolean isBanned) 
	{
		this.isBanned = isBanned;
	}

	public boolean isPasswordRestictions() 
	{
		return passwordRestictions;
	}

	public void setPasswordRestictions(boolean passwordRestictions) 
	{
		this.passwordRestictions = passwordRestictions;
	}
	
	public User()
	{}
	
	public User(String username,String password,Role role,boolean isBanned,boolean passwordRestictions)
	{
		this.username = username;
		this.password = password;
		this.role = role;
		this.isBanned = isBanned;
		this.passwordRestictions = passwordRestictions;
	}
	
	public boolean isPasswordFollowsPasswordRestrictionsOfUser(String password)
	{
		if(!this.passwordRestictions)
			return true;
		else
		{
        	String numberRegex = ".*[0-9].*";	
        	String mathOpRegex = ".*[/ * + -].*";
        	if(!Pattern.matches(numberRegex,password) | !Pattern.matches(mathOpRegex,password))
        	{
        		return false;
        	}else
        	{
        		return true;
        	}
        		
		}
		
	}
	
	@Override
	public String toString() 
	{
		return username+"|"+ password +"|"+ role.name() +"|"+ isBanned +"|"+  passwordRestictions;
	}
	
	@Override
	public boolean equals(Object o)
	{
	    if (this == o)
	        return true;
	    if (o == null)
	        return false;
	    if (getClass() != o.getClass())
	        return false;
	    User user = (User) o;
	    return Objects.equals(username, user.getUsername())
	            && Objects.equals(password,user.getPassword())
	            && Objects.equals(role, user.role)
	            && Objects.equals(isBanned, user.isBanned())
	            && Objects.equals(passwordRestictions, user.isPasswordRestictions());
	}
}
