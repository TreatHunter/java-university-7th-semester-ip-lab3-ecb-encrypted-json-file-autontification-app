package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.file_json_storage_system;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class JsonFileStorageTest 
{
	static JsonFileStorage db;
	
	@BeforeAll
	static public void init() throws Exception
	{
		Path path = Paths.get(JsonFileStorage.DataBasePath);
		Files.deleteIfExists(path);
		db = new JsonFileStorage();
		db.createDatabase("tester");
	}
	
	@Test
	@Order(1)
	public void newFileCreatedTest() throws Exception 
	{
	    ArrayList<User> usersList = new ArrayList<User>();
		usersList.add(new User("ADMIN","",User.Role.admin,false,false));
	    ArrayList<User> res = db.getUsers();
		assertEquals(usersList,res);
	}	
	
	@Test
	@Order(2)
	public void addNewUsersTest() throws Exception
	{
	    ArrayList<User> usersList = new ArrayList<User>();
		usersList.add(new User("ADMIN","",User.Role.admin,false,false));	    
		usersList.add(new User("ADMINd","",User.Role.admin,false,false));
		usersList.add(new User("ded","2",User.Role.admin,false,false));	
		usersList.add(new User("beb","",User.Role.user,false,false));
		usersList.add(new User("lil","",User.Role.admin,true,false));
		usersList.add(new User("mem","",User.Role.admin,false,true));
		db.setUsers(usersList);
	    ArrayList<User> res = db.getUsers();
		assertEquals(usersList,res);		
	}
}
