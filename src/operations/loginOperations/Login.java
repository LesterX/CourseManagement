package operations.loginOperations;

import systemUsers.SystemUserModel;

import java.io.IOException;
import java.util.Scanner;

public class Login
{
	public Login(){}
	
	public static SystemUserModel execute() throws IOException
	{
		//Read user input of their first name, surname, and id
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your ID:");
		String ID = scanner.nextLine();
		System.out.println("Enter your password:");
		String pw = scanner.nextLine();
		
		//Authenticate the user information
		SystemUserModel user = Authentication.execute(ID,pw);
		
		//If user is not found in register, return
		if (user == null)
		{	
			System.out.println("User not found");
			return null;
		}
		else
			System.out.println("Logged In");
		
		return user;
	}
}
