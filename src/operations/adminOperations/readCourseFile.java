package operations.adminOperations;

import offerings.CourseOffering;
import offerings.OfferingFactory;
import registrar.Register;
import system.systemStatus;
import systemUsers.SystemUserModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class readCourseFile 
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
			System.out.println("Only admin can read course file");
			return;
		}
		
		//Read the file name from user input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the name of the course file: ");
		String file_name = br.readLine();
		
		//Given code from testing class
		OfferingFactory factory = new OfferingFactory();
		try
		{
			br = new BufferedReader(new FileReader(new File(file_name)));

			CourseOffering	courseOffering = factory.createCourseOffering(br);
			
			Register.getInstance().registerCourse(courseOffering.getCourseID(), courseOffering);
			
			br = new BufferedReader(new InputStreamReader(System.in));
		}catch(FileNotFoundException e)
		{
			System.out.println("File not found");
			return;
		}
	}
}
