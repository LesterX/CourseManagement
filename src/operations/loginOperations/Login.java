package operations.loginOperations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import java.io.IOException;
import java.util.Scanner;

public class Login
{
	public Login(){}
	
	public static LoggedInAuthenticatedUser execute() throws IOException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your first name:");
		String first_name = scanner.nextLine();
		System.out.println("Enter your surname:");
		String surname = scanner.nextLine();
		System.out.println("Enter your ID:");
		String ID = scanner.nextLine();
	
		
		LoggedInAuthenticatedUser user = Authentication.execute(first_name, surname, ID);
		
		if (user == null)
		{	
			System.out.println("User not found");
			return null;
		}
		else
			System.out.println("Logged In");
		
		user.setID(ID);
		user.setName(first_name);
		user.setSurname(surname);
		return user;
	}
}
