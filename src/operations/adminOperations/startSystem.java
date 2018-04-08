package operations.adminOperations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import system.systemStatus;

public class startSystem {
	public static void execute(LoggedInAuthenticatedUser user) {
		if (!user.get_type().equals("Admin"))
		{
			System.out.println("Only admin can start/stop the system");
			return;
		}
		
		systemStatus.instance().start();
		System.out.println("The system has started");
	}

}
