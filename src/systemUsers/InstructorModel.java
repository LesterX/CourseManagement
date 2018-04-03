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

	public void calculateFinalGrade(CourseOffering course, String ID){
		StudentModel target = null;
		Double finalGrade;
		for(StudentModel studentModel : course.getStudentsEnrolled())
			if (studentModel.getID().equals(ID))
				target = studentModel;
		finalGrade = 0D;
		Weights weights = course.getEvaluationStrategies().get(target.getEvaluationEntities().get(course));
		Marks marks  = target.getPerCourseMarks().get(course);
		weights.initializeIterator();
		while(weights.hasNext()){
			weights.next();
			finalGrade += weights.getCurrentValue() * marks.getValueWithKey(weights.getCurrentKey());
		}
	}
	public void printRecord(CourseOffering course){
		if (isTutorOf.contains(course)){
			System.out.println("ID : " + course.getCourseID() + "\nCourse name : " + course.getCourseName() + "\nSemester : " +
					course.getSemester());
			System.out.println("Students allowed to enroll\n");
			for(StudentModel student : course.getStudentsEnrolled()){
				System.out.println("Student name : " + student.getName() + "\nStudent surname : " + student.getSurname() +
						"\nStudent ID : " + student.getID() + "\nStudent EvaluationType : " +
						student.getEvaluationEntities().get(course) + "\n\n");
			}
		}
		else{
			System.exit(0);
		}
	}
}
