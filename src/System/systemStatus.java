package System;

public class systemStatus {
	
	private boolean status;
	
	private static systemStatus instance;
	
	protected systemStatus(){
		
	}
	
	public static systemStatus instance() {
		if(instance == null) {
			instance = new systemStatus();
		}
		return instance;
	}
	
	public void start() {
		this.status = true;
	}
	
	public void stop() {
		this.status = false;
	}
	
	public boolean status() {
		return status;
	}

}
