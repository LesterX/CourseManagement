package others;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;

import systemUsers.SystemUserForLogin;

public class GenerateUsers 
{
	private static List<SystemUserForLogin> user_list = new ArrayList<SystemUserForLogin>();
	
	public static void generate_users(String file_name) throws IOException
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
	
	public static void generate_users(String input, String file_name) throws IOException
	{
		File file = new File(file_name);
		String type = "";
		
		BufferedReader br = new BufferedReader(new FileReader(input));
		String line = br.readLine();
		line = br.readLine();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file_name, true));
		
		while (line != null)
		{	
			if (line.split("\t")[0].equals("EVALUATION"))
				break;
			
			if (line.split("\t")[0].equals("TUTOR"))
			{
				type = "Instructor";
				line = br.readLine();
				continue;
			}else if (line.split("\t")[0].equals("ELLIGIBLE"))
			{
				type = "Student";
				line = br.readLine();
				continue;
			}else
			{
				try
				{
					if (validate(line.split("\t")[0],line.split("\t")[1],line.split("\t")[2]) == null)
						writer.write(line.split("\t")[0] + " " + line.split("\t")[1] + " " + line.split("\t")[2] + " " + type + "\n");
				}catch (IOException e)
				{
					System.out.println("IO Exception");
				}
			}
			
			line = br.readLine();
		}
		br.close();
		writer.close();
	}
	
	public static String validate(String name, String surname, String ID)
	{
		String type = null;
		for (SystemUserForLogin user : user_list)
		{
			if (user.validate(name, surname, ID))
				type = user.getType();
		}
		
		return type;
	}
	
	public void delete_user(String ID)
	{
		for (SystemUserForLogin user : user_list)
		{
			if (user.getID().equals(ID))
			{	
				user_list.remove(user);
				return;
			}	
		}
	}
}
