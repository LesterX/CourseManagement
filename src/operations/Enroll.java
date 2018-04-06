package operations;

import registrar.ModelRegister;
import offerings.CourseOffering;
import systemUsers.StudentModel;
import authenticatedUsers.LoggedInAuthenticatedUser;
import authenticatedUsers.LoggedInStudent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

public class Enroll 
{
	public Enroll(){}
	
	public static void execute(LoggedInAuthenticatedUser user) throws IOException
	{
		if (user == null || !user.getAuthenticationToken().getUserType().equals("Student"))
		{	
			System.out.println("Only student can enroll");
			return;
		}
		else
		{
			user = (LoggedInStudent) user;
			StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(user.getID());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter the course ID you want to enroll:");
			String course_id = "";
			
			course_id = br.readLine();
			
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
				ModelRegister.getInstance().registerCourse(student.getID(), course);
			}
		}
	}
	
	// Overload the method with another parameter courseID
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
				ModelRegister.getInstance().registerCourse(student.getID(), course);
			}
		}
	}
	
	public void execute(LoggedInAuthenticatedUser user, CourseOffering course) 
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
			
			System.out.println(student.getName());
			
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
				ModelRegister.getInstance().registerCourse(student.getID(), course);
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
