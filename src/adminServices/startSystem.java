package adminServices;

import System.systemStatus;
public class startSystem {
	public startSystem() {
		systemStatus.instance().start();
		System.out.println("the system has started");
		
	}

}
