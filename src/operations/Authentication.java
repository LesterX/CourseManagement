package operations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import systemUsers.SystemUserModel;
import registrar.ModelRegister;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import authenticatedUsers.LoggedInAdmin;
import authenticatedUsers.LoggedInInstructor;
import authenticatedUsers.LoggedInStudent;

public class Authentication 
{
	public LoggedInAuthenticatedUser execute(String first_name, String surname, String ID) throws IOException
	{
		SystemUserModel registered_user = ModelRegister.getInstance().getRegisteredUser(ID);
		if (registered_user.getName().equals(first_name) && registered_user.getSurname().equals(surname) && registered_user.getID().equals(ID))
		{
			//TODO: create user.txt
			String type = get_user_type("users.txt",ID);
			switch (type)
			{
				case "N/A":
					return null;
				case "Admin":
					return new LoggedInAdmin();
				case "Instructor":
					return new LoggedInInstructor();
				case "Student":
					return new LoggedInStudent();
				default:
					return null;
			}
		}
		return null;
	}
	
	private static String get_user_type(String user_file, String ID) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(user_file));
		String line = br.readLine();
		String type = "N/A";
		
		while (line != null)
		{
			if (line.split(" ")[2].equals(ID))
			{
				type = line.split(" ")[3];
				br.close();
				return type;
			}
			line = br.readLine();
		}
		
		br.close();
		return type;
	}
}