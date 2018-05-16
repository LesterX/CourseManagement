package operations.loginOperations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import java.io.IOException;
import authenticatedUsers.LoggedInAdmin;
import authenticatedUsers.LoggedInInstructor;
import authenticatedUsers.LoggedInStudent;
import authenticationServer.AuthenticationToken;
import systemUsers.SystemUserModel;
import registrar.Register;

public class Authentication 
{
	public Authentication(){}
	
	public static LoggedInAuthenticatedUser execute(String first_name, String surname, String ID) throws IOException
	{
		//Search the user's information in the register
		SystemUserModel user_registered = Register.getInstance().getRegisteredUser(ID);
		

		
		//If not found, return
		if (user_registered == null)
			return null;
		
		if (!(first_name.equals(user_registered.getName()) && surname.equals(user_registered.getSurname()) && ID.equals(user_registered.getID())))
		{
			System.out.println("Invalid name or ID");
			return null;
		}
		
		//Create the LoggedInAuthenticatedUser based on their user type
		switch (user_registered.get_type())
		{
			case "Admin":
			{	
				LoggedInAdmin user = new LoggedInAdmin();
				AuthenticationToken token = new AuthenticationToken();
				token.setUserType("Admin");
				user.setAuthenticationToken(token);
				return user;
			}
			case "Instructor":
			{
				LoggedInInstructor user = new LoggedInInstructor();
				AuthenticationToken token = new AuthenticationToken();
				token.setUserType("Instructor");
				user.setAuthenticationToken(token);
				return user;
			}
			case "Student":
			{
				LoggedInStudent user = new LoggedInStudent();
				AuthenticationToken token = new AuthenticationToken();
				token.setUserType("Student");
				user.setAuthenticationToken(token);
				return user;
			}
			default:
				return null;
		}
	}
}
