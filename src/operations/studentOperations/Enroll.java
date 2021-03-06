package operations.studentOperations;

import registrar.Register;
import system.systemStatus;
import offerings.CourseOffering;
import offerings.ICourseOffering;
import systemUsers.StudentModel;
import systemUsers.SystemUserModel;
import customDatatypes.Marks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Enroll 
{
	public Enroll(){}
	
	public static void execute(SystemUserModel user) throws IOException
	{
		//If the system is closed or the user is not Student type, return
		if (!systemStatus.instance().status())
		{
			System.out.println("System is closed");
			return;
		}
		if (user == null || !user.get_type().equals("Student"))
		{	
			System.out.println("Only student can enroll");
			return;
		}
		else
		{
			StudentModel student = (StudentModel) user;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			//Read course id from user input
			System.out.println("Enter the course ID you want to enroll:");
			String course_id = "";
			
			course_id = br.readLine();
			
			CourseOffering course = Register.getInstance().getRegisteredCourse(course_id);
			if (course == null)
			{
				//Return if course if not found
				System.out.println("Course not found");
				return;
			}else if (!found_in_list(student, course.getStudentsAllowedToEnroll()))
			{
				//Return if the student is not allowed to enroll
				System.out.println("You are not allowed to enroll this course");
				return;
			}else if (found_in_list(student, course.getStudentsEnrolled()))
			{
				//Return if the student is already enrolled in this course
				System.out.println("You are already enrolled in this course");
				return;
			}else
			{
				//Add the record
				course.getStudentsEnrolled().add(student);
				student.getCoursesEnrolled().add(course);
				
				//Initialize the student's marks if it is not created
				if (student.getPerCourseMarks() == null)
					student.setPerCourseMarks(new HashMap<ICourseOffering,Marks>());

				Map<ICourseOffering,Marks> student_marks = student.getPerCourseMarks();
				student_marks.put(course, new Marks());
				student.setPerCourseMarks(student_marks);
				
				//Enroll the student into this course in the register
				Register.getInstance().registerCourse(student.getID(), course);
			}
		}
	}
	
	// Overload the method with another parameter courseID
	public void execute(SystemUserModel user, String courseID) 
	{
		if (!user.get_type().equals("Student"))
		{	
			System.out.println("Only student can enroll");
			return;
		}
		else
		{
			StudentModel student = (StudentModel) user;
			String course_id = courseID;
			
			CourseOffering course = Register.getInstance().getRegisteredCourse(course_id);
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
				Register.getInstance().registerCourse(student.getID(), course);
			}
		}
	}
	
	public void execute(SystemUserModel user, CourseOffering course) 
	{
		if (!user.get_type().equals("Student"))
		{	
			System.out.println("Only student can enroll");
			return;
		}
		else
		{
			StudentModel student = (StudentModel) user;
			
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
				Register.getInstance().registerCourse(student.getID(), course);
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
