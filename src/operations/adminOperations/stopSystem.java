package operations.adminOperations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import system.systemStatus;

public class stopSystem {
	public static void execute(LoggedInAuthenticatedUser user) {
		if (!user.get_type().equals("Admin"))
		{
			System.out.println("Only admin can start/stop the system");
			return;
		}
		
		systemStatus.instance().stop();
		System.out.println("the system is now stopped");
	}

}