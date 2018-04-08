package operations.adminOperations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import authenticatedUsers.LoggedInAuthenticatedUser;
import offerings.CourseOffering;
import registrar.ModelRegister;
import system.systemStatus;

public class PrintAllCourses 
{
	public static void execute(LoggedInAuthenticatedUser user) throws IOException
	{
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
		
		ArrayList<CourseOffering> courses = (ArrayList<CourseOffering>) ModelRegister.getInstance().getAllCourses();
		Iterator<CourseOffering> iter = courses.iterator();
		while (iter.hasNext())
		{
			CourseOffering course = iter.next();
			System.out.println(course.getCourseID());
		}
		
		return;
	}
}
