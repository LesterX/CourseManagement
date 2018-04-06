package operations;

import registrar.ModelRegister;
import systemUsers.StudentModel;
import authenticatedUsers.LoggedInAuthenticatedUser;
import java.util.Scanner;
import offerings.CourseOffering;
import customDatatypes.Marks;

public class PrintRecord 
{
	public PrintRecord(){}
	
	public static void execute(LoggedInAuthenticatedUser user)
	{
		if (!user.getAuthenticationToken().getUserType().equals("Student"))
		{	
			System.out.println("Only student can enroll");
			return;
		}
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the course ID: ");
		String course_id = scanner.nextLine();
		
		CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(course_id);
		StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(user.getID());
		
		if (course == null)
		{
			System.out.println("Course not found");
			return;
		}
		if (student.getPerCourseMarks() == null)
		{
			System.out.println("No marks available");
			return;
		}
		
		Marks mark = student.getPerCourseMarks().get(course);
		mark.initializeIterator();
		
		System.out.println(course.getCourseName());
		
		while (mark.hasNext())
		{
			System.out.println(mark.getCurrentKey() + "		" + mark.getCurrentValue());
			mark.next();
		}
	}
	
	public static void execute(LoggedInAuthenticatedUser user, CourseOffering course)
	{
		if (!user.getAuthenticationToken().getUserType().equals("Student"))
		{	
			System.out.println("Only student can enroll");
			return;
		}
		
		StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(user.getID());
		
		if (course == null)
		{
			System.out.println("Course not found");
			return;
		}
		
		Marks mark = student.getPerCourseMarks().get(course);
		mark.initializeIterator();
		
		System.out.println(course.getCourseName());
		
		while (mark.hasNext())
		{
			System.out.println(mark.getCurrentKey() + "		" + mark.getCurrentValue());
			mark.next();
		}
	}
	
	public void execute(LoggedInAuthenticatedUser user, String course_id)
	{
		if (!user.getAuthenticationToken().getUserType().equals("Student"))
		{	
			System.out.println("Only student can print records");
			return;
		}
		
		CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(course_id);
		StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(user.getID());
		
		if (course == null)
		{
			System.out.println("Course not found");
			return;
		}
		
		Marks mark = student.getPerCourseMarks().get(course);
		mark.initializeIterator();
		
		System.out.println(course.getCourseName());
		
		while (mark.hasNext())
		{
			System.out.println(mark.getCurrentKey() + "		" + mark.getCurrentValue());
			mark.next();
		}
	}
}
