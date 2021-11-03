package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.DataBaseSystem;
import java.util.ArrayList;

import org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.file_json_storage_system.JsonFileStorage;
import org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.file_json_storage_system.User;

public class DatabaseSystem 
{
	ArrayList<User> users = new ArrayList<User>();
	JsonFileStorage storage;
	
	public DatabaseSystem() throws Exception
	{
		storage = new JsonFileStorage();
		users = storage.getUsers();
	}
	
	public User getUserByUsername(String username)
	{
		return users.stream().filter(user -> username.equals(user.getUsername())).findFirst().orElse(null);
		
	}
	
	public ArrayList<User> getUsers()
	{
		return users;
	}
	
	public void SaveChangesToFile() throws Exception 
	{
		storage.setUsers(users);
	}
	
/*	public void changeUserParametrsByUsername(User user) 
	{	
		users.forEach(System.out::println);
		User old = users.stream().filter(curUser -> user.getUsername().equals(curUser.getUsername())).findFirst().orElse(null);
		int idex = users.indexOf(old);
		users.set(idex, user);
		users.forEach(System.out::println);
	}*/
}
