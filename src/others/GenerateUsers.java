package others;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import systemUsers.SystemUserForLogin;

public class GenerateUsers 
{
	private List<SystemUserForLogin> user_list = new ArrayList<SystemUserForLogin>();
	
	public void generate_users(String file_name) throws IOException
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file_name));

			String line = br.readLine();
			while (line != null)
			{
				String name = line.split(" ")[0];
				String surname = line.split(" ")[1];
				String ID = line.split(" ")[2];
				String type = line.split(" ")[3];
				SystemUserForLogin user = new SystemUserForLogin(name,surname,ID,type);
				user_list.add(user);
				
				line = br.readLine();
			}
		}catch (IOException e) {System.out.println("Users File Not Found");}
	}
	
	public String validate(String name, String surname, String ID)
	{
		String type = null;
		for (SystemUserForLogin user : user_list)
		{
			if (user.validate(name, surname, ID))
				type = user.getType();
		}
		
		return type;
	}
}
