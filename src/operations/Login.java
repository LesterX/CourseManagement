package operations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import java.io.IOException;
import java.util.Scanner;

public class Login
{
	public LoggedInAuthenticatedUser execute() throws IOException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your first name:");
		String first_name = scanner.nextLine();
		System.out.println("Enter your surname:");
		String surname = scanner.nextLine();
		System.out.println("Enter your ID:");
		String ID = scanner.nextLine();
		
		scanner.close();
		
		Authentication aut = new Authentication();
		LoggedInAuthenticatedUser user = aut.execute(first_name, surname, ID);
		
		if (user == null)
			System.out.println("User not found");
		
		return user;
	}
}
