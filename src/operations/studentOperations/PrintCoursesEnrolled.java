package operations.studentOperations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import systemUsers.StudentModel;
import registrar.ModelRegister;
import offerings.ICourseOffering;
import java.util.List;

public class PrintCoursesEnrolled 
{
	public static void execute(LoggedInAuthenticatedUser user)
	{
		if (!user.getAuthenticationToken().getUserType().equals("Student"))
		{	
			System.out.println("Only student can print enrolled courses");
			return;
		}
		
		StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(user.getID());
		
		if (student == null)
			return;
		
		List<ICourseOffering> enrolled = student.getCoursesEnrolled();
		for (ICourseOffering course : enrolled)
		{
			System.out.println(course.getCourseID());
		}
	}
}
