package operations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import systemUsers.SystemUserModel;
import registrar.ModelRegister;
import loggedInUserFactory.LoggedInUserFactory;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;;

public class Authentication 
{
	public LoggedInAuthenticatedUser execute(String first_name, String surname, String ID) throws FileNotFoundException
	{
		LoggedInAuthenticatedUser user;
		SystemUserModel registered_user = ModelRegister.getInstance().getRegisteredUser(ID);
		if (registered_user.getName().equals(first_name) && registered_user.getSurname().equals(surname) && registered_user.getID().equals(ID))
		{
			user = new LoggedInUserFactory().createAuthenticatedUser() //TODO: need a parameter of AuthenticationToken
		}
		
		
		/**
		String[] usernames = new String[1000];
		String[] passwords = new String[1000];
		String[] firstnames = new String[1000];
		String[] surnames = new String[1000];
		int[] ID = new int[1000];
		String[] user_types = new String[1000];
		
		int num = 0;
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("users.txt"));
			String line = br.readLine();
			while (line != null)
			{
				String[] parts = line.split(" ");
				usernames[num] = parts[0];
				passwords[num] = parts[1];
				firstnames[num] = parts[2];
				surnames[num] = parts[3];
				ID[num] = Integer.parseInt(parts[4]);
				user_types[num] = parts[5];
				num++;
				line = br.readLine();
			}
		}catch (IOException e) {
			System.out.println("IO Exception");
		}
		
		for (int i = 0; i < num; i ++)
		{
			if (usernames[i].equals(username) && passwords[i].equals(password))
			{
				System.out.println("Name: " + firstnames[i] + " " + surnames[i] + " " + ID[i]);
				if (user_types[i].equals("Admin")) {user = new LoggedInAdmin();}
				else if (user_types[i].equals("Instructor")) {user = new LoggedInInstructor();}
				else {user = new LoggedInStudent();}
				
				return user;
			}else if (i == num - 1)
			{
				System.out.println("Invalid username or ID");
			}
		}
		*/
		return null;
	}
}
