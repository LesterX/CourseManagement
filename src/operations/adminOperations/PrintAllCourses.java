package operations.adminOperations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import offerings.CourseOffering;
import registrar.Register;
import system.systemStatus;
import systemUsers.SystemUserModel;

public class PrintAllCourses 
{
	public static void execute(SystemUserModel user) throws IOException
	{
		////If the system is closed or the user is not Admin type, return
		if (!systemStatus.instance().status())
		{
			System.out.println("System is closed");
			return;
		}
		
		if (!user.get_type().equals("Admin"))
		{
			System.out.println("Only admin can print courses");
			return;
		}
		
		//Read courses from the register
		ArrayList<CourseOffering> courses = (ArrayList<CourseOffering>) Register.getInstance().getAllCourses();
		Iterator<CourseOffering> iter = courses.iterator();
		while (iter.hasNext())
		{
			CourseOffering course = iter.next();
			System.out.println(course.getCourseID());
		}
		
		return;
	}
}
