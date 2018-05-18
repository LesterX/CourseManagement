package operations.studentOperations;

import systemUsers.StudentModel;
import systemUsers.SystemUserModel;
import registrar.Register;
import system.systemStatus;
import customDatatypes.NotificationTypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SetNotifPref 
{
	public SetNotifPref(){}
	
	public static void execute(SystemUserModel user, NotificationTypes np)
	{
		//If the system is closed or the user is not Student type, return
		if (!systemStatus.instance().status())
		{
			System.out.println("System is closed");
			return;
		}
		if (!user.get_type().equals("Student"))
		{	
			System.out.println("Only student can set notification preference");
			return;
		}
		
		StudentModel student = (StudentModel) Register.getInstance().getRegisteredUser(user.getID());
		
		student.setNotificationType(np);
	}
	
	public static void execute(SystemUserModel user, String np_string)
	{
		//If the system is closed or the user is not Student type, return
		NotificationTypes np;
		if (!user.get_type().equals("Student"))
		{	
			System.out.println("Only student can set notification preference");
			return;
		}
		
		StudentModel student = (StudentModel) Register.getInstance().getRegisteredUser(user.getID());
		
		//Read notification type from user input
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
	
	public static void execute(SystemUserModel user) throws NumberFormatException, IOException
	{
		if (!user.get_type().equals("Student"))
		{	
			System.out.println("Only student can set notification preference");
			return;
		}
		
		StudentModel student = (StudentModel) Register.getInstance().getRegisteredUser(user.getID());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter the index of the notification type you prefer: ");
		System.out.println("1. Email	2. Cellphone	3. Pigeon Post (really?)");
		int choice = Integer.parseInt(br.readLine());
		
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
