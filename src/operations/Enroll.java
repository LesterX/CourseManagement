package operations;

import registrar.ModelRegister;
import offerings.CourseOffering;
import systemUsers.StudentModel;
import authenticatedUsers.LoggedInAuthenticatedUser;
import authenticatedUsers.LoggedInStudent;
import java.util.Scanner;
import java.util.List;

public class Enroll 
{
	public Enroll(){}
	
	public void execute(LoggedInAuthenticatedUser user) 
	{
		if (!user.getAuthenticationToken().getUserType().equals("Student"))
		{	
			System.out.println("Only student can enroll");
			return;
		}
		else
		{
			user = (LoggedInStudent) user;
			StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(user.getID());
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the course ID you want to enroll:");
			String course_id = scanner.nextLine();
			scanner.close();
			
			CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(course_id);
			if (course == null)
			{
				System.out.println("Course not found");
				return;
			}else if (!found_in_list(student, course.getStudentsAllowedToEnroll()))
			{
				System.out.println("You are not allowed to enroll this course");
				return;
			}else if (found_in_list(student, course.getStudentsEnrolled()))
			{
				System.out.println("You are already enrolled in this course");
				return;
			}else
			{
				course.getStudentsEnrolled().add(student);
				student.getCoursesEnrolled().add(course);
			}
		}
	}
	
	// Override the method with another parameter courseID
	public void execute(LoggedInAuthenticatedUser user, String courseID) 
	{
		if (!user.getAuthenticationToken().getUserType().equals("Student"))
		{	
			System.out.println("Only student can enroll");
			return;
		}
		else
		{
			user = (LoggedInStudent) user;
			StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(user.getID());
			String course_id = courseID;
			
			CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(course_id);
			if (course == null)
			{
				System.out.println("Course not found");
				return;
			}else if (!found_in_list(student, course.getStudentsAllowedToEnroll()))
			{
				System.out.println("You are not allowed to enroll this course");
				return;
			}else if (found_in_list(student, course.getStudentsEnrolled()))
			{
				System.out.println("You are already enrolled in this course");
				return;
			}else
			{
				course.getStudentsEnrolled().add(student);
				student.getCoursesEnrolled().add(course);
			}
		}
	}
	
	private static boolean found_in_list(StudentModel s, List<StudentModel> l)
	{
		for (StudentModel e : l)
		{
			if (s.equals(e))
				return true;
		}
		
		return false;
	}
}
