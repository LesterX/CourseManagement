package operations.loginOperations;

import java.io.IOException;
import systemUsers.SystemUserModel;
import registrar.Register;

public class Authentication 
{
	public Authentication(){}
	
	public static SystemUserModel execute(String id, String pw) throws IOException
	{
		//Search the user's information in the register
		SystemUserModel user_registered = Register.getInstance().getRegisteredUser(id);
		
		//If not found, return
		if (user_registered == null)
			return null;
		
		if (!(user_registered.getID().equals(id) && user_registered.getPassword().equals(pw)))
		{
			System.out.println("Invalid ID or password");
			return null;
		}
		
		return user_registered;
	}
}
