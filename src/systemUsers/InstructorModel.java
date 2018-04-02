package systemUsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import customDatatypes.Marks;
import offerings.CourseOffering;
import offerings.ICourseOffering;

public class InstructorModel implements IInstructorModel {

	private String name;
	private String surname;
	private String ID;
	private List<ICourseOffering> isTutorOf;
	
	public InstructorModel(){
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
	
	public List<ICourseOffering> getIsTutorOf() {
		return isTutorOf;
	}
	
	public void setIsTutorOf(List<ICourseOffering> courses) {
		this.isTutorOf = courses;
	}


	public void addMark(Marks mark, CourseOffering course, String studentID){

		StudentModel target = null;
		for(StudentModel studentModel : course.getStudentsEnrolled())
			if (studentModel.getID().equals(studentID))
				target = studentModel;

		target.getPerCourseMarks().get(course).addToEvalStrategy(mark.getCurrentKey(),mark.getCurrentValue());

	}

	public void changeMark(Marks mark, CourseOffering course, String studentID){

		StudentModel target = null;
		Map<ICourseOffering, Marks> newmark = new HashMap<>();
		newmark.put(course, mark);

		for(StudentModel studentModel : course.getStudentsEnrolled())
			if (studentModel.getID().equals(studentID))
				target = studentModel;

		target.setPerCourseMarks(newmark);

		while (target.getPerCourseMarks().get(course).hasNext()){
			String var = target.getPerCourseMarks().get(course).getNextEntry().getKey();
			if (var.equals(mark.getCurrentKey())){
				Double val = mark.getCurrentValue();
			}
		}

	}
	
	
	
}
