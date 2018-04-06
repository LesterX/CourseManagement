package operations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import java.io.IOException;
import authenticatedUsers.LoggedInAdmin;
import authenticatedUsers.LoggedInInstructor;
import authenticatedUsers.LoggedInStudent;
import authenticationServer.AuthenticationToken;
import systemUsers.SystemUserModel;
import registrar.ModelRegister;

public class Authentication 
{
	public Authentication(){}
	
	public static LoggedInAuthenticatedUser execute(String first_name, String surname, String ID) throws IOException
	{
		SystemUserModel user_registered = ModelRegister.getInstance().getRegisteredUser(ID);
		
		
		if (user_registered == null)
			return null;
		
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
