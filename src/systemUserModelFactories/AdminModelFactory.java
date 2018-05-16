package systemUserModelFactories;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import database.IDatabase;
import offerings.ICourseOffering;
import registrar.Register;
import systemUsers.AdminModel;
import systemUsers.InstructorModel;
import systemUsers.StudentModel;
import systemUsers.SystemUserModel;

public class AdminModelFactory implements ISystemUserModelFactory {

	public AdminModel createSystemUserModel(BufferedReader br, ICourseOffering course) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	//Generate data from txt file and input data into registrar and database
	public void generate_from_file(String file_name)
	{
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(file_name)));
			String line = br.readLine();
			while (line != null)
			{
				String line_list[] = line.split(" ");
				
				//Input should have 5 elements every line
				if (line.length() != 5)
				{
					System.out.println("Invalid input");
					return;
				}
				
				String id = line_list[0];
				String name = line_list[1];
				String surname = line_list[2];
				String password = line_list[3];
				String type = line_list[4];
			
				SystemUserModel user;
				
				switch (type)
				{
					case "Admin":
					{	
						user = new AdminModel();
						break;
					}
					case "Instructor":
					{
						user = new InstructorModel();
						break;
					}
					case "Student":
					{
						user = new StudentModel();
						break;
					}
					default:
						return;
				}
				
				user.setID(id);
				user.setName(name);
				user.setSurname(surname);
				user.setPassword(password);
				Register.getInstance().registerUser(id, user);
			}
		} catch (FileNotFoundException e) 
		{
			System.out.println(e.getMessage());
		} catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void generate_from_db(IDatabase db)
	{
		
	}
}

