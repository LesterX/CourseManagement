package systemUsers;

public class AdminModel implements IAdminModel{
	
	private String name;
	private String surname;
	private String ID;
	private String password;
	private String type = "Admin";
	
	public AdminModel() {}
	
	public String get_type()
	{
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}

	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String p)
	{
		password = p;
	}
}
