package operations.adminOperations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import offerings.CourseOffering;
import offerings.OfferingFactory;
import registrar.ModelRegister;
import system.systemStatus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class readCourseFile 
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
			System.out.println("Only admin can read course file");
			return;
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the name of the course file: ");
		String file_name = br.readLine();
		
//		Create an instance of an OfferingFactory
		OfferingFactory factory = new OfferingFactory();
		try
		{
			br = new BufferedReader(new FileReader(new File(file_name)));
			
	//		Use the factory to populate as many instances of courses as many files we've got.
			CourseOffering	courseOffering = factory.createCourseOffering(br);
			
			ModelRegister.getInstance().registerCourse(courseOffering.getCourseID(), courseOffering);
			
			br = new BufferedReader(new InputStreamReader(System.in));
		}catch(FileNotFoundException e)
		{
			System.out.println("File not found");
			return;
		}
	}
}
