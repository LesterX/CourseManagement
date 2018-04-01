package operations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import java.io.IOException;
import java.util.Scanner;

public class Login
{
	public LoggedInAuthenticatedUser execute() throws IOException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your username:");
		String username = scanner.nextLine();
		System.out.println("Enter your password:");
		String password = scanner.nextLine();
		
		Authentication aut = new Authentication();
		try
		{
			LoggedInAuthenticatedUser user = aut.execute(username, password);
			return user;
		}catch (IOException e) {System.out.println("IO Exception");}
		
		return null;
	}
}
