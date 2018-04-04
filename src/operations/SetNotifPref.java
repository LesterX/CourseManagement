package operations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import systemUsers.StudentModel;
import registrar.ModelRegister;
import customDatatypes.NotificationTypes;
import java.util.Scanner;

public class SetNotifPref 
{
	public SetNotifPref(){}
	
	public void execute(LoggedInAuthenticatedUser user, NotificationTypes np)
	{
		if (!user.getAuthenticationToken().getUserType().equals("Student"))
		{	
			System.out.println("Only student can set notification preference");
			return;
		}
		
		StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(user.getID());
		
		student.setNotificationType(np);
	}
	
	public void execute(LoggedInAuthenticatedUser user, String np_string)
	{
		NotificationTypes np;
		if (!user.getAuthenticationToken().getUserType().equals("Student"))
		{	
			System.out.println("Only student can set notification preference");
			return;
		}
		
		StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(user.getID());
		
		if (np_string.toUpperCase().equals("EMAIL"))
			np = NotificationTypes.EMAIL;
		else if (np_string.toUpperCase().equals("CELLPHONE"))
			np = NotificationTypes.CELLPHONE;
		else if ((np_string.toUpperCase().equals("PIGEON_POST")) || (np_string.toUpperCase().equals("PIGEON POST")))
			np = NotificationTypes.PIGEON_POST;
		else
		{
			System.out.println("Invalid notification type");
			return;
		}
		
		student.setNotificationType(np);
	}
	
	public void execute(LoggedInAuthenticatedUser user)
	{
		if (!user.getAuthenticationToken().getUserType().equals("Student"))
		{	
			System.out.println("Only student can set notification preference");
			return;
		}
		
		StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(user.getID());
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the index of the notification type you prefer: ");
		System.out.println("1. Email	2. Cellphone	3. Pigeon Post (really?)");
		int choice = scanner.nextInt();
		scanner.close();
		
		switch (choice)
		{
			case 1:
				student.setNotificationType(NotificationTypes.EMAIL);
			case 2:
				student.setNotificationType(NotificationTypes.CELLPHONE);
			case 3:
				student.setNotificationType(NotificationTypes.PIGEON_POST);
			default:
				return;
		}
	}
}