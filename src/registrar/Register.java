package registrar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.DatabaseServer;
import database.IDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import offerings.CourseOffering;
import offerings.OfferingFactory;
import systemUserModelFactories.SystemUserModelFactory;
import systemUsers.SystemUserModel;

public class Register {
//	Map maintaining copies of existing SystemUserModel objects mapped using their Unique IDs
	private Map<String, SystemUserModel> modelRegister = new HashMap<String, SystemUserModel>();
//	Map maintaining copies of existing CourseOffering objects mapped using their Unique IDs
	private Map<String, CourseOffering> courseRegister = new HashMap<String, CourseOffering>();
	
//	this is a classic implementation of the singleton design pattern
	private static Register instance;
	private static IDatabase db;
	
	private Register(){
	}
	
	public static Register getInstance(){
		if(instance == null)
		{	
			instance = new Register();
			db = new DatabaseServer("course_management");
			db.initiate();
		}
		return instance;
	}
//	the method names should be selfExplanatory
	public boolean checkIfUserHasAlreadyBeenCreated(String ID){
		return modelRegister.containsKey(ID);
	}
	
	public void registerUser(String ID, SystemUserModel user){
		modelRegister.put(ID, user);
		db.insert_user(user.getID(), user.getPassword(), user.getName(), user.getSurname(), user.get_type());
	}
	
	public SystemUserModel getRegisteredUser(String ID){
		return modelRegister.get(ID);
	}
	
	public boolean checkIfCourseHasAlreadyBeenCreated(String ID){
		return courseRegister.containsKey(ID);
	}
	
	public void registerCourse(String ID, CourseOffering course){
		courseRegister.put(ID, course);
	}
	
	public CourseOffering getRegisteredCourse(String ID){
		return courseRegister.get(ID.toUpperCase());
	}
	
	public List<CourseOffering> getAllCourses(){
		List<CourseOffering> courses = new ArrayList<CourseOffering>();
		courses.addAll(courseRegister.values());
		return courses;
	}

	public List<SystemUserModel> getAllUsers(){
		List<SystemUserModel> users = new ArrayList<SystemUserModel>();
		users.addAll(modelRegister.values());
		return users;
	}
	
	//Read input file and add the new users
	public void add_user_from_file(String file_name)
	{
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(file_name)));
			String line = br.readLine();
			SystemUserModelFactory u_factory = new SystemUserModelFactory();
			
			while (line != null)
			{
				SystemUserModel user = u_factory.generate_from_file(line);
				registerUser(user.getID(), user);
				line = br.readLine();
			}
		} catch (FileNotFoundException e) 
		{
			System.out.println(e.getMessage());
		} catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Read input file and add the new course
	public void add_course_from_file(String file_name)
	{
		CourseOffering course = new OfferingFactory().generate_from_file(file_name);
		registerCourse(course.getCourseID(),course);
	}
}
