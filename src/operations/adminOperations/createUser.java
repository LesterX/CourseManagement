package operations.adminOperations;

import systemUsers.*;
import registrar.Register;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import system.*;

public class createUser
{
	public static void execute(SystemUserModel user) throws IOException
	{
		//If the system is closed or the user is not Admin type, return
		if (!systemStatus.instance().status())
		{
			System.out.println("System is closed");
			return;
		}
		
		if (!user.get_type().equals("Admin"))
		{
			System.out.println("Only admin can create user");
			return;
		}
		
		//Read user input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the user type you want to create:\n1.Admin\n2.Instructor\n3.Student");
		String in = br.readLine();
		System.out.println("Enter the user's first name: ");
		String first_name = br.readLine();
		System.out.println("Enter the user's surname: ");
		String surname = br.readLine();
		System.out.println("Enter the user's ID: ");
		String id = br.readLine();
		
		//Create new user based on the type the user entered
		switch(in)
		{
			case "1":
			{ 		
				AdminModel new_user = new AdminModel();
				new_user.setName(first_name);
				new_user.setSurname(surname);
				new_user.setID(id);
				Register.getInstance().registerUser(id, new_user);
				break;
			}
			case "2":
			{
				InstructorModel new_user = new InstructorModel();
				new_user.setName(first_name);
				new_user.setSurname(surname);
				new_user.setID(id);
				Register.getInstance().registerUser(id, new_user);
				break;
			}
			case "3":
			{
				StudentModel new_user = new StudentModel();
				new_user.setName(first_name);
				new_user.setSurname(surname);
				new_user.setID(id);
				Register.getInstance().registerUser(id, new_user);
				break;
			}
			default:
			{
				System.out.println("Invalid Input");
				return;
			}
		}
	}
}
