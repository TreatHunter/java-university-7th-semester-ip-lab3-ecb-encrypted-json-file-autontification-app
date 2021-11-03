package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.file_json_storage_system;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileStorage 
{
	public static final String DataBasePath = "database.json";
	
	private ObjectMapper mapper = new ObjectMapper();
	private Path path = Paths.get(DataBasePath);
	
	public JsonFileStorage() throws IOException 
	{
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		if(!Files.exists(path))
		{
			path = Files.createFile(path);
		    List<User> usersList = Arrays.asList(
					new User("ADMIN","",User.Role.admin,false,false)
		    );
		    mapper.writeValue(path.toFile(), usersList);
		}
	}
	
	public ArrayList<User> getUsers() throws Exception
	{
		List<User> users = Arrays.asList(mapper.readValue(path.toFile(), User[].class));
		return new ArrayList<User>(users);
	}
	
	public void setUsers(ArrayList<User> usersList) throws Exception
	{
		mapper.writeValue(path.toFile(), usersList);
	}
}
