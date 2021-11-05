package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.file_json_storage_system;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.ecb.encryption.AES;

public class JsonFileStorage 
{
	public static final String DataBasePath = "database.json";
	
	private ObjectMapper mapper = new ObjectMapper();
	private Path path = Paths.get(DataBasePath);
	private boolean existDatabase;
	
	public JsonFileStorage() throws Exception 
	{
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		if(Files.exists(path))
		{
			existDatabase = true;
			return;
		}
		existDatabase = false;
	}
	
	public boolean isExistDatabase()
	{
		return existDatabase;
	}
	
	public void setKey(String key) 
	{
		AES.setKey(key);
	}
	
	public void createDatabase(String key) throws Exception
	{
		AES.setKey(key);
		path = Files.createFile(path);
	    ArrayList<User> usersList = new ArrayList<User>();
	    usersList.add(new User("ADMIN","",User.Role.admin,false,false));
	    setUsers(usersList);
	    existDatabase = true;
	}
	
	public ArrayList<User> getUsers() throws Exception
	{
		String jsonStr = readFromEcncryptedFile();
		ArrayList<User> listCar = mapper.readValue(jsonStr, new TypeReference<ArrayList<User>>(){});
		return listCar;
	}
	
	private String readFromEcncryptedFile() throws Exception 
	{
		String bs64Str = Files.readAllLines(path).get(0);
		return AES.decrypt(bs64Str);
	}
	
	public void setUsers(ArrayList<User> usersList) throws Exception
	{
		String jsonStr = mapper.writeValueAsString(usersList);
		writeToEncrytpedFile(jsonStr);
	}
	
	private void writeToEncrytpedFile(String String) throws IOException 
	{
		String bs64String = AES.encrypt(String);
		Files.write(path, bs64String.getBytes());
	}

}
