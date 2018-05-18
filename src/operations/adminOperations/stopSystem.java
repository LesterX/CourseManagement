package operations.adminOperations;

import system.systemStatus;
import systemUsers.SystemUserModel;

public class stopSystem {
	public static void execute(SystemUserModel user) {
		if (!user.get_type().equals("Admin"))
		{
			System.out.println("Only admin can start/stop the system");
			return;
		}
		
		systemStatus.instance().stop();
		System.out.println("the system is now stopped");
	}

}
