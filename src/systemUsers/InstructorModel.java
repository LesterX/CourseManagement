package systemUsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import customDatatypes.Marks;
import customDatatypes.Weights;
import offerings.CourseOffering;
import offerings.ICourseOffering;

public class InstructorModel implements IInstructorModel {
	private String type = "Instructor";
	private String name;
	private String surname;
	private String ID;
	private String password;
	private List<ICourseOffering> isTutorOf;
	
	public InstructorModel(){
	}
	
	public String get_type()
	{
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String p)
	{
		password = p;
	}
	
	public List<ICourseOffering> getIsTutorOf() {
		return isTutorOf;
	}
	
	public void setIsTutorOf(List<ICourseOffering> courses) {
		this.isTutorOf = courses;
	}

}
