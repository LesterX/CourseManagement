package operations.adminOperations;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import authenticatedUsers.LoggedInAuthenticatedUser;
import registrar.ModelRegister;
import system.systemStatus;
import systemUsers.SystemUserModel;

public class PrintAllUsers 
{
	public static void execute(LoggedInAuthenticatedUser user) throws IOException
	{
		if (!systemStatus.instance().status())
		{
			System.out.println("System is closed");
			return;
		}
		
		if (!user.get_type().equals("Admin"))
		{
			System.out.println("Only admin can print users");
			return;
		}
		
		ArrayList<SystemUserModel> users = (ArrayList<SystemUserModel>) ModelRegister.getInstance().getAllUsers();
		Iterator<SystemUserModel> iter = users.iterator();
		while (iter.hasNext())
		{
			SystemUserModel system_user = iter.next();
			System.out.println(system_user.get_type() + "  " + system_user.getID() + "  " + system_user.getName() + "  " + system_user.getSurname());
		}
		
		return;
	}
}
