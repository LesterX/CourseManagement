package operations.studentOperations;

import registrar.ModelRegister;
import systemUsers.StudentModel;
import authenticatedUsers.LoggedInAuthenticatedUser;
import java.util.Scanner;
import offerings.CourseOffering;

public class PrintRecord 
{
	public PrintRecord(){}
	
	public static void execute(LoggedInAuthenticatedUser user) throws RuntimeException
	{
		if (user == null || !user.getAuthenticationToken().getUserType().equals("Instructor"))
		{
			throw new RuntimeException("Only instructors can print record");
		}
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the course ID: ");
		String course_id = scanner.nextLine();
		
		CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(course_id);
		StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(user.getID());
		
		if (course == null)
		{
			throw new RuntimeException("No course has been found");
		}

		System.out.println("ID : " + course.getCourseID() + "\nCourse name : " + course.getCourseName() + "\nSemester : " +
				course.getSemester());
		System.out.println("Students allowed to enroll\n");
		for(StudentModel student_temp : course.getStudentsEnrolled()){
			System.out.println("Student name : " + student_temp.getName() + "\nStudent surname : " + student_temp.getSurname() +
					"\nStudent ID : " + student_temp.getID() + "\nStudent EvaluationType : " +
					student_temp.getEvaluationEntities().get(course) + "\n\n");
		}

	}
	
	public static void execute(LoggedInAuthenticatedUser user, CourseOffering course)
	{
		if (user == null || !user.getAuthenticationToken().getUserType().equals("Instructor"))
		{
			throw new RuntimeException("Only instructors can print record");
		}

		if (course == null)
		{
			throw new RuntimeException("No course has been found");
		}

		System.out.println("ID : " + course.getCourseID() + "\nCourse name : " + course.getCourseName() + "\nSemester : " +
				course.getSemester());
		System.out.println("Students allowed to enroll\n");
		for(StudentModel student_temp : course.getStudentsEnrolled()){
			System.out.println("Student name : " + student_temp.getName() + "\nStudent surname : " + student_temp.getSurname() +
					"\nStudent ID : " + student_temp.getID() + "\nStudent EvaluationType : " +
					student_temp.getEvaluationEntities().get(course) + "\n\n");
		}

	}
	
	public void execute(LoggedInAuthenticatedUser user, String course_id)
	{
		if (user == null || !user.getAuthenticationToken().getUserType().equals("Instructor"))
		{
			throw new RuntimeException("Only instructors can print record");
		}

		CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(course_id);

		if (course == null)
		{
			throw new RuntimeException("No course has been found");
		}

		System.out.println("ID : " + course.getCourseID() + "\nCourse name : " + course.getCourseName() + "\nSemester : " +
				course.getSemester());
		System.out.println("Students allowed to enroll\n");
		for(StudentModel student_temp : course.getStudentsEnrolled()){
			System.out.println("Student name : " + student_temp.getName() + "\nStudent surname : " + student_temp.getSurname() +
					"\nStudent ID : " + student_temp.getID() + "\nStudent EvaluationType : " +
					student_temp.getEvaluationEntities().get(course) + "\n\n");
		}

	}
}
