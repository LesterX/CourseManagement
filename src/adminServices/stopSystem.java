package adminServices;
import System.systemStatus;
public class stopSystem {
	public stopSystem() {
		systemStatus.instance().stop();
		System.out.println("the system is now stopped");
	}

}
