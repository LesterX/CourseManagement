package operations.studentOperations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import systemUsers.StudentModel;
import registrar.Register;
import system.systemStatus;
import offerings.ICourseOffering;
import java.util.List;

public class PrintCoursesEnrolled 
{
	public static void execute(LoggedInAuthenticatedUser user)
	{
		//If the system is closed or the user is not Student type, return
		if (!systemStatus.instance().status())
		{
			System.out.println("System is closed");
			return;
		}
		if (!user.getAuthenticationToken().getUserType().equals("Student"))
		{	
			System.out.println("Only student can print enrolled courses");
			return;
		}
		
		StudentModel student = (StudentModel) Register.getInstance().getRegisteredUser(user.getID());
		
		if (student == null)
			return;
		
		//Print the courses that the student is already enrolled
		List<ICourseOffering> enrolled = student.getCoursesEnrolled();
		for (ICourseOffering course : enrolled)
		{
			System.out.println(course.getCourseID());
		}
	}
}
